package uk.co.ribot.androidboilerplate.ui.profile;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface ProfileView extends MvpView {

    void setToolbarColor(String hexColor);
    void setImage(String url);
    void setFullName(String fullName);
    void setEmail(String email);
    void setBio(String bio);
    void setBirthDate(String birthDate);
    void setActive(boolean active);

    void launchEmailApp(String email);

    void showError(String message);
}
