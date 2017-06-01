package rybak.agata.models;

import javax.swing.*;
import java.util.List;

/**
 * Created by asus on 2017-05-04.
 */
public class CustomComboboxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T>{

    private List<T> items;
    private T selectedItem;

    public CustomComboboxModel(List<T> items) {
        this.items = items;
        if(items != null && !items.isEmpty())
        {
            selectedItem = items.get(0);
        }
    }

    public void update(List<T> items) {
        this.items = items;
        if(items != null && !items.isEmpty())
        {
            selectedItem = items.get(0);
        }
    }

    @Override
    public T getElementAt(int idx) {
        if (idx < 0 || idx >= items.size())
        {
            return null;
        }
        return items.get(idx);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (T)anItem;
    }


}