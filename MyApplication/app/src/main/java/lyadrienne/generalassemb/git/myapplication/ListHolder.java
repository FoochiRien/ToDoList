package lyadrienne.generalassemb.git.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/23/17.
 */

public class ListHolder {

    private static  ListHolder sInstance;


    private List<Listlist> mListEvent;

    private ListHolder() {
        mListEvent = new ArrayList<>();
    }

    public static ListHolder getsInstance(){
        if(sInstance == null){
            sInstance = new ListHolder();
        } return sInstance;
    }

    public void addListHolder(Listlist listList){
        mListEvent.add(listList);
    }

    public void removeListHolder(Listlist listlist){
        mListEvent.remove(listlist);
    }

    public void setmListEvent(List<Listlist> mListEvent) {
        this.mListEvent = mListEvent;
    }

    public List<Listlist> getmListEvent(){
        return mListEvent;
    }


}
