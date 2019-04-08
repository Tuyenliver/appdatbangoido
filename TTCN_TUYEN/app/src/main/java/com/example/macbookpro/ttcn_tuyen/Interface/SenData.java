package com.example.macbookpro.ttcn_tuyen.Interface;

import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.Model.Order;

public interface SenData {
    public void sent(Danhsach ds,int sl);
    public void click();
}
