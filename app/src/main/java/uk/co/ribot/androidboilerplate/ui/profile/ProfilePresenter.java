package uk.co.ribot.androidboilerplate.ui.profile;

import javax.inject.Inject;

import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

public class ProfilePresenter extends BasePresenter<ProfileView> {

    @Inject
    public ProfilePresenter() {}

    public void setUpProfile(Profile profile) {
        if (profile == null) {
            getMvpView().showError("failed to set up profile info");
        }

        getMvpView().setToolbarColor(profile.hexColor());
        getMvpView().setImage(profile.avatar());
        getMvpView().setFullName(profile.name().first() + profile.name().last());
        getMvpView().setBirthDate(profile.dateOfBirth().toString());
        getMvpView().setEmail(profile.email());
        getMvpView().setBio(profile.bio());
    }
}
