package uk.co.ribot.androidboilerplate.ui.detail;

import android.content.DialogInterface;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.util.DialogFactory;

public class DetailActivity extends BaseActivity implements DetailView {

    public static String EXTRA_RIBOT = "ribot";

    @Inject DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        presenter.attachView(this);

        Ribot ribot = getIntent().getParcelableExtra(EXTRA_RIBOT);
        presenter.setUpRibotDetail(ribot);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
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
