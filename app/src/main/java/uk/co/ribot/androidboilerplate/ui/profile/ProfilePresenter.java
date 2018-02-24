package uk.co.ribot.androidboilerplate.ui.profile;

import android.text.TextUtils;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

public class ProfilePresenter extends BasePresenter<ProfileView> {

    private Profile profile;

    @Inject
    public ProfilePresenter() {}

    public void setUpProfile(Profile profile) {
        if (profile == null) {
            getMvpView().showError("failed to set up profile info");
        }

        this.profile = profile;

        getMvpView().setToolbarColor(profile.hexColor());
        if (!TextUtils.isEmpty(profile.avatar())) {
            getMvpView().setImage(profile.avatar());
        }

        getMvpView().setFullName(profile.name().first() + " " + profile.name().last());
        getMvpView().setBirthDate(new SimpleDateFormat("dd MMM yyyy").format(profile.dateOfBirth()));
        getMvpView().setEmail(profile.email());
        if (!TextUtils.isEmpty(profile.bio())) {
            getMvpView().setBio(profile.bio());
        }
        getMvpView().setActive(profile.active());
    }

    public void handleEmailClick() {
        getMvpView().launchEmailApp(profile.email());
    }
}
