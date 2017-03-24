package lyadrienne.generalassemb.git.myapplication;

/**
 * Created by Admin on 3/23/17.
 */

public class Listlist2 {

    private String mItemList, mItemDescrip;

    public Listlist2(String itemList, String itemDescrip) {
        this.mItemList = itemList;
        this.mItemDescrip = itemDescrip;
    }


    public String getItemTitle() {
        return mItemList;
    }

    public void setmItemList(String itemList) {
        this.mItemList = itemList;
    }

    public String getItemDescrip() {
        return mItemDescrip;
    }

    public void setmItemDescrip(String itemDescrip) {
        this.mItemDescrip = itemDescrip;
    }


}
