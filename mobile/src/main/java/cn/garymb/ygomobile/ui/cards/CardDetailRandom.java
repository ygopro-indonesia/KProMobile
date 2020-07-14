package cn.garymb.ygomobile.ui.cards;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.garymb.ygomobile.lite.R;
import cn.garymb.ygomobile.loader.ImageLoader;
import cn.garymb.ygomobile.ui.activities.BaseActivity;
import cn.garymb.ygomobile.ui.plus.VUiKit;
import cn.garymb.ygomobile.utils.CardUtils;
import cn.garymb.ygomobile.utils.ThreeDLayoutUtil;
import ocgcore.CardManager;
import ocgcore.DataManager;
import ocgcore.StringManager;
import ocgcore.data.Card;
import ocgcore.enums.CardType;

import static android.view.View.inflate;

public class CardDetailRandom {
    private static ImageView cardImage;
    private static TextView name;
    private static TextView desc;
    private static TextView level;
    private static TextView type;
    private static TextView race;
    private static TextView cardAtk;
    private static TextView cardDef;
    private static TextView attrView;
    private static View linkArrow;
    private static View monsterlayout;
    private static View layout_detail_p_scale;
    private static View atkdefView;
    private static TextView detail_cardscale;
    private static CardManager mCardManager;
    private static ImageLoader imageLoader;
    private static StringManager mStringManager;
    private static SparseArray<Card> cards;
    private static View view;

    public static void RandomCardDetail(BaseActivity context, Card cardInfo) {
        ThreeDLayoutUtil viewCardDetail = (ThreeDLayoutUtil) inflate(context, R.layout.dialog_cardinfo_small, null);
        cardImage = viewCardDetail.findViewById(R.id.card_image_toast);
        name = viewCardDetail.findViewById(R.id.card_name_toast);
        level = viewCardDetail.findViewById(R.id.card_level_toast);
        linkArrow = viewCardDetail.findViewById(R.id.link_arrows_toast);
        race = viewCardDetail.findViewById(R.id.card_race_toast);
        attrView = viewCardDetail.findViewById(R.id.card_attr_toast);
        type = viewCardDetail.findViewById(R.id.card_type_toast);
        cardAtk = viewCardDetail.findViewById(R.id.card_atk_toast);
        cardDef = viewCardDetail.findViewById(R.id.card_def_toast);
        atkdefView = viewCardDetail.findViewById(R.id.layout_atkdef2_toast);
        desc = viewCardDetail.findViewById(R.id.text_desc_toast);

        layout_detail_p_scale = viewCardDetail.findViewById(R.id.detail_p_scale);
        detail_cardscale = viewCardDetail.findViewById(R.id.detail_cardscale);

        if (cardInfo == null) return;
        mStringManager = DataManager.get().getStringManager();
        imageLoader = ImageLoader.get(context);
        imageLoader.bindImage(cardImage, cardInfo.Code, null, true);
        name.setText(cardInfo.Name);
        type.setText(CardUtils.getAllTypeString(cardInfo, mStringManager).replace("/", "|"));
        attrView.setText(mStringManager.getAttributeString(cardInfo.Attribute));
        desc.setText(cardInfo.Desc);
        if (cardInfo.isType(CardType.Monster)) {
            atkdefView.setVisibility(View.VISIBLE);
            race.setVisibility(View.VISIBLE);
            String star = "★" + cardInfo.getStar();
            level.setText(star);
            if (cardInfo.isType(CardType.Xyz)) {
                level.setTextColor(context.getResources().getColor(R.color.star_rank));
            } else {
                level.setTextColor(context.getResources().getColor(R.color.star));
            }
            cardAtk.setText((cardInfo.Attack < 0 ? "?" : String.valueOf(cardInfo.Attack)));
            if (cardInfo.isType(CardType.Pendulum)) {
                desc.setTextSize(10);
            }
            //连接怪兽设置
            if (cardInfo.isType(CardType.Link)) {
                level.setVisibility(View.GONE);
                linkArrow.setVisibility(View.VISIBLE);
                cardDef.setText((cardInfo.getStar() < 0 ? "?" : "LINK-" + String.valueOf(cardInfo.getStar())));
                BaseActivity.showLinkArrows(cardInfo, view);
            } else {
                level.setVisibility(View.VISIBLE);
                linkArrow.setVisibility(View.GONE);
                cardDef.setText((cardInfo.Defense < 0 ? "?" : String.valueOf(cardInfo.Defense)));
            }
            race.setText(mStringManager.getRaceString(cardInfo.Race));
        } else {
            atkdefView.setVisibility(View.GONE);
            race.setVisibility(View.GONE);
            level.setVisibility(View.GONE);
            linkArrow.setVisibility(View.GONE);
        }
        Toast toast = new Toast(context);
        toast.setView(viewCardDetail);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.LEFT, 50, 0);
        toast.show();
    }
}
