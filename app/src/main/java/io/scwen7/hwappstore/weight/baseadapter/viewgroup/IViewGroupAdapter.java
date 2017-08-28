package io.scwen7.hwappstore.weight.baseadapter.viewgroup;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;


public interface IViewGroupAdapter  {

    View getView(ViewGroup parent, int pos);


    int getCount();

    void notifyDatasetChanged();

    void registerDataSetObserver(DataSetObserver dataSetObserver);

    void unregisterDataSetObserver(DataSetObserver dataSetObserver);
}
