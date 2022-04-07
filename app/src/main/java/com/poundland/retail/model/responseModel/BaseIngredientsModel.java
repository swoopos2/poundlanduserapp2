package com.poundland.retail.model.responseModel;

import java.util.List;

public class BaseIngredientsModel {
    HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean productAddonsHeader;
    List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> addonsList;

    public HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean getProductAddonsHeader() {
        return productAddonsHeader;
    }

    public void setProductAddonsHeader(HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean productAddonsHeader) {
        this.productAddonsHeader = productAddonsHeader;
    }

    public List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> getAddonsList() {
        return addonsList;
    }

    public void setAddonsList(List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> addonsList) {
        this.addonsList = addonsList;
    }
}
