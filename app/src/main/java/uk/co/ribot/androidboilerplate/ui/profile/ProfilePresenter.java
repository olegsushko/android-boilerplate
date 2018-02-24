package uk.co.ribot.androidboilerplate.ui.profile;

import android.text.TextUtils;

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

        if (!TextUtils.isEmpty(profile.avatar())) {
            getMvpView().setCollapsingToolbarColor(profile.hexColor());
            getMvpView().setImage(profile.avatar());
        } else {
            getMvpView().setToolbarColor(profile.hexColor());
        }

        getMvpView().setFullName(profile.name().first() + profile.name().last());
        getMvpView().setBirthDate(profile.dateOfBirth().toString());
        getMvpView().setEmail(profile.email());
        if (!TextUtils.isEmpty(profile.bio())) {
            getMvpView().setBio(profile.bio());
        }
        getMvpView().setActive(profile.active());
    }
}
