package uk.co.ribot.androidboilerplate.ui.profile;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.util.DialogFactory;

public class ProfileActivity extends BaseActivity implements ProfileView {

    public static String EXTRA_PROFILE = "profile";

    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.image_avatar) ImageView avatarImage;
    @BindView(R.id.text_birth_date) TextView birthDateText;
    @BindView(R.id.text_email) TextView emailText;
    @BindView(R.id.text_bio) TextView bioText;
    @BindView(R.id.button_email) FloatingActionButton emailButton;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject
    ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ProfileTheme);
        activityComponent().inject(this);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter.attachView(this);

        Profile profile = getIntent().getParcelableExtra(EXTRA_PROFILE);
        presenter.setUpProfile(profile);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void setToolbarColor(String hexColor) {
        collapsingToolbarLayout.setContentScrimColor(Color.parseColor(hexColor));
        collapsingToolbarLayout.setStatusBarScrimColor(Color.parseColor(hexColor));
    }

    @Override
    public void setImage(String url) {
        Picasso.with(this)
                .load(url)
                .into(avatarImage);
    }

    @Override
    public void setFullName(String fullName) {
        getSupportActionBar().setTitle(fullName);
    }

    @Override
    public void setEmail(String email) {
        emailText.setText(email);
    }

    @Override
    public void setBio(String bio) {
        bioText.setText(bio);
    }

    @Override
    public void setBirthDate(String birthDate) {
        birthDateText.setText(birthDate);
    }

    @Override
    public void setActive(boolean active) {
        // for some reason ribot service returns false for isActive property for all profiles
//        emailButton.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String message) {
        DialogFactory.createGenericErrorDialog(this, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        }).show();
    }
}
