package uk.co.ribot.androidboilerplate.ui.detail;

import javax.inject.Inject;

import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

public class DetailPresenter extends BasePresenter<DetailView> {

    @Inject
    public DetailPresenter() {}

    public void setUpRibotDetail(Ribot ribot) {
        if (ribot == null) {
            getMvpView().showError("failed to set up detail info");
        }


    }
}
