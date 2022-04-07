package com.poundland.retail.interfaces;

import java.util.HashMap;
import java.util.List;

public interface FilterListner {

    void onApplyFilter(HashMap<String, List<String>> data);

}
