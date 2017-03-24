package lyadrienne.generalassemb.git.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/23/17.
 */

public class Listlist {

    private String mListTitle;

    private List<Listlist2> listlist2;

    public Listlist(String listTitle) {

        this.mListTitle = listTitle;
        listlist2 = new ArrayList<>();
    }

    public List<Listlist2> getListlist2() {
        return listlist2;
    }

    public String getmListTitle() {
        return mListTitle;
    }

    public void setmListTitle(String listTitle) {
        this.mListTitle = listTitle;
    }

    public void addmListlist2(Listlist2 totalitem){
        listlist2.add(totalitem);
    }

}
