package uk.co.ribot.androidboilerplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

import uk.co.ribot.androidboilerplate.data.model.Name;
import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.ui.profile.ProfilePresenter;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileView;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePresenterTest {

    @Mock
    ProfileView mockProfileView;

    private ProfilePresenter presenter;

    @Before
    public void setUp() {
        presenter = new ProfilePresenter();
        presenter.attachView(mockProfileView);
    }

    @Test
    public void sets_profile_data() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        Date date = calendar.getTime();



        Profile profile = Profile.builder()
                .setName(Name.create("first", "last"))
                .setAvatar("imageurl")
                .setHexColor("hexcolor")
                .setEmail("email")
                .setDateOfBirth(date)
                .setBio("bio")
                .setActive(true)
                .build();

        presenter.setUpProfile(profile);

        verify(mockProfileView).setToolbarColor("hexcolor");
        verify(mockProfileView).setFullName("first last");
        verify(mockProfileView).setImage("imageurl");
        verify(mockProfileView).setBio("bio");
        verify(mockProfileView).setBirthDate("01 Jan 1990");
        verify(mockProfileView).setActive(true);
    }

    @Test
    public void does_not_set_profile_bio_if_it_is_null() {
        Profile profile = Profile.builder()
                .setName(Name.create("first", "last"))
                .setAvatar("imageurl")
                .setHexColor("hexcolor")
                .setEmail("email")
                .setDateOfBirth(new Date())
                .setActive(true)
                .build();

        presenter.setUpProfile(profile);

        verify(mockProfileView, never()).setBio(anyString());
    }

    @Test
    public void does_not_set_profile_avatar_if_it_is_null() {
        Profile profile = Profile.builder()
                .setName(Name.create("first", "last"))
                .setHexColor("hexcolor")
                .setEmail("email")
                .setDateOfBirth(new Date())
                .setActive(true)
                .build();

        presenter.setUpProfile(profile);

        verify(mockProfileView, never()).setImage(anyString());
    }

    @Test
    public void does_not_set_profile_data_if_it_is_null() {
        presenter.setUpProfile(null);

        verify(mockProfileView, never()).setToolbarColor(anyString());
        verify(mockProfileView, never()).setImage(anyString());
        verify(mockProfileView, never()).setFullName(anyString());
        verify(mockProfileView, never()).setBirthDate(anyString());
        verify(mockProfileView, never()).setEmail(anyString());
        verify(mockProfileView, never()).setBio(anyString());
        verify(mockProfileView, never()).setActive(anyBoolean());
    }

    @Test
    public void launches_email_app_on_email_button_click() {
        Profile profile = Profile.builder()
                .setEmail("email")
                .setHexColor("hexcolor")
                .setName(Name.create("first", "last"))
                .setDateOfBirth(new Date())
                .setActive(true)
                .build();

        presenter.setUpProfile(profile);
        presenter.handleEmailClick();

        verify(mockProfileView).launchEmailApp("email");
    }
}
