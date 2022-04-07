package com.poundland.retail.activityHospitality;

public class SingleProductDetailActivityGITTTTT
      /*  extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, ModifierSelectionListner,
        DrawerListner, ComboOfferListner */{
 /*   private ActivitySingleProductDetailsBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private List<SingleVenueDetailResponseModel.VenueDetailsBean.CategoriesBean> categoryList;
    private ArrayList<String> imageList;
    private List<SingleProductDetailResponseModel.ProductRatingBean.ReviewsBean> reviewList;
    private List<List<SingleProductDetailResponseModel.ComboOfferBean>> comboOfferList;
    private ArrayList<SingleProductDetailResponseModel.ProductsBean.ModifierListBean> modifierList;
    private String modifierName = "";
    private String currentModifier = "";
    private String couponCode = "";
    private int modifierId, merchantId;
    private int quantity = 1;
    private String costPerProduct, venueId, productId, offerId = "";
    private SingleProductDetailActivityGITTTTT instance = null;
    private int secondAdapterPos = 0;
    private String format = "HH:mm:ss";
    private String formatSameDayShipping = "HH:mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);
    private SimpleDateFormat sdfSameDayShipping = new SimpleDateFormat(formatSameDayShipping);
    private String currentTime;
    private String currentTimeSameDayShipping;
    private SingleProductDetailResponseModel productDetailResponseModel;
    private static CountDownTimer mCountDownTimer = null;
    private HashMap<String, String> requestHashMap = new HashMap<>();
    private String barcodeId = "";
    private boolean setAlpha;
    String PATTERN_START_WITH_DATE_NO_YEAR = "dd MMM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_product_details);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        getTotalCartCount();
    }

    private void init() {
        instance = this;
        onNewIntent(getIntent());

        setAlpha = false;
        binding.tvAddToCart.setAlpha(1f);
        binding.tvAddToWishlist.setAlpha(1f);
        binding.tvBuyNow.setAlpha(1f);

        binding.tvBuyAndGetFree.setVisibility(View.GONE);
        binding.rvBuyAndGetFree.setVisibility(View.GONE);
        binding.rlSameDayDelivery.setVisibility(View.GONE);
        binding.llBuyBulk.setVisibility(View.GONE);


        binding.tvAddToCart.setText(getString(R.string.add_to_cart));
        binding.tvAddToCart.setBackgroundResource(R.drawable.light_red_filled_rounded_rect);
        binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        currentTimeSameDayShipping = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        modifierId = 0;
        venueId = getIntent().getStringExtra(STORE_ID);
        productId = getIntent().getStringExtra(PRODUCT_ID);
        barcodeId = getIntent().getStringExtra(BARCODE_ID);
        offerId = getIntent().getStringExtra(OFFER_ID);

        isLoading = true;
        pageNumber = 1;

        categoryList = new ArrayList<>();
        imageList = new ArrayList<>();
        reviewList = new ArrayList<>();
        comboOfferList = new ArrayList<>();
        modifierList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        setListeners();
        getProductDetails();

    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.rlViewStore.setOnClickListener(this);
        binding.tvAddToCart.setOnClickListener(this);
        binding.tvAddToWishlist.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.tvBuyNow.setOnClickListener(this);
        binding.tvAddToCartBb.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    private void setReviewAdaper() {
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviewList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvTopReview.setLayoutManager(layoutManager);
        binding.rvTopReview.setAdapter(reviewAdapter);
    }

    private void setComboAdaper() {
        ComboOfferAdapter reviewAdapter = new ComboOfferAdapter(this, comboOfferList, currentTime, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvComboOffer.setLayoutManager(layoutManager);
        binding.rvComboOffer.setAdapter(reviewAdapter);
    }

    private void setModifierAdaper() {
        ModifierAdapter reviewAdapter = new ModifierAdapter(this, modifierList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvModifier.setLayoutManager(layoutManager);
        binding.rvModifier.setAdapter(reviewAdapter);
    }

    @Override
    public void onModifierSelect(int pos_1st, int pos_2nd) {
      *//*  try {
            requestHashMap.put(modifierList.get(pos_1st).getType(), modifierList.get(pos_1st).getType_list().get(pos_2nd));
            List<String> modifierRequestList = new ArrayList<>();
            for (Map.Entry<String, String> entry : requestHashMap.entrySet()) {
                modifierRequestList.add(entry.getValue());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                modifierName = String.join(",", modifierRequestList);
            }
            currentModifier = modifierList.get(pos_1st).getType_list().get(pos_2nd);
            getProductDetailByModifier();
            log("modifierRequestList", "" + requestHashMap.toString() + "\n" + modifierName + " crnt==>" + currentModifier);
        } catch (Exception exc) {
            exc.printStackTrace();
        }*//*
    }

    @Override
    public void onModifierDeselect(int pos_1st, int pos_2nd) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_to_cart_bb:

                if (binding.tvAddToCartBb.getText().toString().equalsIgnoreCase(getString(R.string.add_to_cart)) || binding.tvAddToCartBb.getText().toString().equalsIgnoreCase(getString(R.string.go_to_bag))) {
                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                        addToCartBulk();
                    } else {
                        DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                addToCartBulk();
                            }

                            @Override
                            public void onNegativeClick() {
                            }
                        });
                    }
                }

                break;
            case R.id.tv_add_to_cart:
                if (!setAlpha) {
                    if (binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.notify_me))) {
                        notifyMe();
                    } else if (binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.add_to_cart)) || binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.go_to_bag))) {
                        if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                            prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                            addToCart(false, false);
                        } else {
                            DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                    addToCart(false, false);
                                }

                                @Override
                                public void onNegativeClick() {
                                }
                            });
                        }
                    } *//*else if (binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.go_to_bag))) {   // go to checkout
                    Intent goToCart = new Intent(this, MyCartActivity.class);
                    startActivity(goToCart);
                }*//*
                }
                break;

            case R.id.rl_view_store:
                Intent topStore = new Intent(this, VenueDetailActivity.class);
                topStore.putExtra(STORE_ID, venueId);
                topStore.putExtra(FROM_WHERE, HOME_RETAIL);
                startActivity(topStore);
                break;

            case R.id.tv_add_to_wishlist:
                if (!setAlpha) {
                    likeDislike();
                }
                break;

            case R.id.tv_buy_now:
                if (!setAlpha) {
                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                        addToCart(true, false);
                    } else {
                        DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                addToCart(true, false);
                            }

                            @Override
                            public void onNegativeClick() {

                            }
                        });
                    }
                }
                break;
            case R.id.iv_cart:
                Intent intent = new Intent(this, MyCartActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setData(SingleProductDetailResponseModel data) {

        productId = String.valueOf(data.getProducts().getId());
        venueId = String.valueOf(data.getProducts().getVenue_id());

        if (data.getProducts().getBulk_selling_qty() > 0) {
            binding.llBuyBulk.setVisibility(View.VISIBLE);
            binding.tvBbSetPrice.setText("" + data.getProducts().getBulk_selling_qty() + " at " + getString(R.string.pound) + data.getProducts().getBulk_selling_price());
        } else {
            binding.llBuyBulk.setVisibility(View.GONE);
        }


        if (data.getVenues().getDelivery() == 1 && data.getVenues().getCollection() == 1) {
            binding.tvDeliveryType.setText(getString(R.string.home_delivery) + "\n" + getString(R.string.click_n_collect));
        } else if (data.getVenues().getDelivery() == 1) {
            binding.tvDeliveryType.setText(getString(R.string.home_delivery));
        } else if (data.getVenues().getCollection() == 1) {
            binding.tvDeliveryType.setText(getString(R.string.click_n_collect));
        }
        //String mainId =data.getVenues().getId() ;
        binding.tvStoreName.setText(data.getVenues().getVenue_name());
        binding.tvProductName.setText(data.getProducts().getProduct_name());

        Glide.with(instance).load(ApiRequestUrl.BASE_URL_VENUE + data.getVenues().getVenue_images().get(0)).apply(new RequestOptions()
                .placeholder(R.drawable.ic_store_blue))
                .into(binding.ivHomeImg);
        if (data.getProducts().getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvDescription.setText(Html.fromHtml(data.getProducts().getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                binding.tvDescription.setText(Html.fromHtml(data.getProducts().getProduct_description()));
            }
        }

        binding.tvMinOrder.setText(getString(R.string.min_order) + data.getVenues().getHome_delivery_mov());

        if (data.getProducts().getAvl_quantity() > 0) {
            if ((!TextUtils.isEmpty(offerId)))
                binding.tvMrp.setPaintFlags(binding.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvMrp.setText(getString(R.string.pound) + data.getProducts().getSelling_price());
        } else {
            binding.tvMrpLevel.setVisibility(View.GONE);
            binding.tvMrp.setText(getString(R.string.out_of_stock));
            binding.tvAddToCart.setText(getString(R.string.notify_me));
            binding.tvBuyNow.setVisibility(View.GONE);
            binding.tvAddToWishlist.setVisibility(View.GONE);
        }

        if (data.getProductOfers() != null && data.getProductOfers().getOffer_title() != null) {
            binding.tvNewPrice.setText(getString(R.string.pound) + data.getProducts().getNew_price() + " (" + data.getProductOfers().getOffer_title() + ")");
        } else {
            binding.tvNewPrice.setText(getString(R.string.pound) + data.getProducts().getNew_price());
            binding.tvMrpLevel.setVisibility(View.GONE);
            binding.tvMrp.setVisibility(View.GONE);
        }

        binding.rbRating.setRating(Float.parseFloat(data.getProductRating().getRating_value()));

        if (data.getProductRating().getCount() < 1) {
            binding.tvRatingCount.setText(getString(R.string.no_rating));
        } else
            binding.tvRatingCount.setText("(" + data.getProductRating().getCount() + " Ratings)");

        if (data.getProducts().isWishlisted()) {
            binding.tvAddToWishlist.setText(getString(R.string.wishlisted));
            binding.tvAddToWishlist.setBackgroundResource(R.drawable.green_filled_rounded_rect);
            binding.tvAddToWishlist.setTextColor(getResources().getColor(R.color.color_white));
        }
        *//*if (data.getProducts().isCart()) {
            binding.tvAddToCart.setText(getString(R.string.go_to_bag));
        } else binding.tvAddToCart.setText(getString(R.string.add_to_cart));*//*
        try {

            if (offerId == null || offerId.equals("") || offerId.equals("0")) {
                binding.llDealExpire.setVisibility(View.GONE);
            } else {
                String endTime = data.getProductOfers().getOffer_time_end();
                Date dateObj1 = sdf.parse(currentTime);
                Date dateObj2 = sdf.parse(endTime);
                dismissCountDownTimer();
                setTimer(dateObj2, dateObj1);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            binding.llDealExpire.setVisibility(View.GONE);
        }
    }

    private void setTimer(Date dateObj2, Date dateObj1) {
        mCountDownTimer = new CountDownTimer(dateObj2.getTime() - dateObj1.getTime(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                binding.tvExpiry.setText(hms);
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer.start();
    }

    public void dismissCountDownTimer() {
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
    }

    private void setDataModifier(ProductDetailByModifierResponseModel data) {
        modifierId = data.getProducts().getId();
        productId = String.valueOf(data.getProducts().getProduct_id());

        binding.tvAddToCart.setText(getString(R.string.add_to_cart));
        binding.tvAddToCart.setBackgroundResource(R.drawable.light_red_filled_rounded_rect);
        binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
        if (data.getProducts().getAvl_quantity() > 0) {
            binding.tvMrp.setPaintFlags(binding.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvMrp.setText(getString(R.string.pound) + data.getProducts().getSelling_price());
            binding.tvNewPrice.setText(getString(R.string.pound) + data.getProducts().getNew_price());

            if (data.getProducts().getOffer_title() != null && !data.getProducts().getOffer_title().equals("")) {
                binding.tvNewPrice.setText(getString(R.string.pound) + data.getProducts().getNew_price() + " (" + data.getProducts().getOffer_title() + ")");
            } else
                binding.tvNewPrice.setText(getString(R.string.pound) + data.getProducts().getNew_price());

        } else {
            binding.tvMrpLevel.setVisibility(View.GONE);
            binding.tvMrp.setText(getString(R.string.out_of_stock));
            binding.tvAddToCart.setText(getString(R.string.notify_me));
            binding.tvBuyNow.setVisibility(View.GONE);
            binding.tvAddToWishlist.setVisibility(View.GONE);
        }

        if (data.getProducts().getModifier_images() != null && data.getProducts().getModifier_images().size() > 0) {
            imageList.clear();
            imageList.addAll(data.getProducts().getModifier_images());
            setViewPager();
        }
    }

    @Override
    public void onRefresh() {
        init();
        binding.tvAddToCart.setText(getString(R.string.add_to_cart));
        binding.tvAddToCartBb.setText(getString(R.string.add_to_cart));
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {

    }

    @Override
    public void onComboOfferListner(int pos) {
        List<AddToCartComboRequestModel.CartsBean> comboRequestModelList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < comboOfferList.get(pos).size(); i++) {
            ids.add(String.valueOf(comboOfferList.get(pos).get(i).getId()));
        }
        ids.add(productId);

        comboRequestModelList.add(new AddToCartComboRequestModel.CartsBean(venueId, String.valueOf(merchantId),
                String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), productId,
                String.valueOf(modifierId), String.valueOf(offerId), quantity,
                costPerProduct, couponCode, "", TextUtils.join(",", ids), costPerProduct));

        for (int i = 0; i < comboOfferList.get(pos).size(); i++) {
            comboRequestModelList.add(new AddToCartComboRequestModel.CartsBean(venueId,
                    "" + comboOfferList.get(pos).get(i).getMerchant_id(),
                    String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                    "" + comboOfferList.get(pos).get(i).getId(),
                    "" + comboOfferList.get(pos).get(i).getModifier_id(),
                    "" + comboOfferList.get(pos).get(i).getDiscount_id(),
                    quantity, comboOfferList.get(pos).get(i).getSelling_price(), "", "",
                    TextUtils.join(",", ids), comboOfferList.get(pos).get(i).getSelling_price()));
        }
        AddToCartComboRequestModel comboRequestModel = new AddToCartComboRequestModel(comboRequestModelList);
        addToCartCombo(comboRequestModel);
    }

    private void addToCartCombo(AddToCartComboRequestModel comboRequestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addMultipleCarts(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), comboRequestModel);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            binding.tvAddToCart.setText(getString(R.string.go_to_bag));
                            //binding.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rounded_rect);
                            binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {

                                getTotalCartCount();

                            } else
                                showSnackBar(binding.getRoot(), cartResponseModel.getMessage());
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddToCartResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void addToCart(boolean openCart, boolean isCombo) {
       *//* if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;

            requestModel = new AddToCartRequestModel
                    (String.valueOf(merchantId), venueId, productId, String.valueOf(modifierId),
                            offerId, String.valueOf(quantity), costPerProduct, couponCode, "", "0");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addToCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {
                                if (openCart) {
                                    Intent goToCart = new Intent(instance, MyCartActivity.class);
                                    startActivity(goToCart);
                                } else {
                                    if (!isCombo) {
                                        binding.tvAddToCart.setText(getString(R.string.go_to_bag));
                                        //   binding.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rounded_rect);
                                        binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
                                    }
                                }

                                getTotalCartCount();

                            } else {
                                showSnackBar(binding.getRoot(), cartResponseModel.getMessage());
                            }

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddToCartResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }*//*
    }

    private void getProductDetailByModifier() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ProductDetailByModifierRequestModel requestModel = new ProductDetailByModifierRequestModel(venueId, productId,
                    modifierName, currentModifier, offerId);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ProductDetailByModifierResponseModel> call = apiInterface.getProductDetailByModifier(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<ProductDetailByModifierResponseModel>() {
                @Override
                public void onResponse(Call<ProductDetailByModifierResponseModel> call, Response<ProductDetailByModifierResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            ProductDetailByModifierResponseModel modifierResponseModel = response.body();

                            if (modifierResponseModel.isStatus()) {
                                if (modifierResponseModel.getProducts() != null) {
                                    setAlpha = false;
                                    binding.tvAddToCart.setAlpha(1f);
                                    binding.tvAddToWishlist.setAlpha(1f);
                                    binding.tvBuyNow.setAlpha(1f);
                                    setDataModifier(modifierResponseModel);
                                } else {
                                    showSnackBar(binding.getRoot(), getString(R.string.no_modifier_data_found));
                                    modifierId = 0;
                                    setAlpha = true;
                                    binding.tvAddToCart.setAlpha(.5f);
                                    binding.tvAddToWishlist.setAlpha(.5f);
                                    binding.tvBuyNow.setAlpha(.5f);
                                }
                            } else {
                                showSnackBar(binding.getRoot(), modifierResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ProductDetailByModifierResponseModel> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void getProductDetails() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleProductDetailRequestModel requestModel = new SingleProductDetailRequestModel
                    (productId, String.valueOf(modifierId), offerId, latitude, longitude, barcodeId, "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SingleProductDetailResponseModel> call = apiInterface.getProductDetail(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<SingleProductDetailResponseModel>() {
                @Override
                public void onResponse(Call<SingleProductDetailResponseModel> call, Response<SingleProductDetailResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            productDetailResponseModel = response.body();
                            if (productDetailResponseModel.isStatus()) {

                                setData(productDetailResponseModel);
                                setSameDayDelivery(productDetailResponseModel);

                                if (productDetailResponseModel.getProducts().getImages() != null && productDetailResponseModel.getProducts().getImages().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);

                                    merchantId = productDetailResponseModel.getProducts().getMerchant_id();
                                    modifierId = productDetailResponseModel.getProducts().getModifier_id();
                                    costPerProduct = productDetailResponseModel.getProducts().getSelling_price();
                                    imageList.clear();
                                    // (myCartList.get(position).getModifier_images()) ?  myCartList.get(position).getProduct_image() :  myCartList.get(position).getModifier_images()
                                    if (productDetailResponseModel.getProducts().getModifier_images() != null && productDetailResponseModel.getProducts().getModifier_images().size() > 0) {
                                        imageList.addAll(productDetailResponseModel.getProducts().getModifier_images());
                                    } else {
                                        imageList.addAll(productDetailResponseModel.getProducts().getImages());
                                    }
                                    setViewPager();

                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    imageList.clear();
                                    setViewPager();
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (productDetailResponseModel.getProductRating().getReviews() != null &&
                                        productDetailResponseModel.getProductRating().getReviews().size() > 0) {
                                    reviewList.clear();
                                    reviewList.addAll(productDetailResponseModel.getProductRating().getReviews());
                                    setReviewAdaper();
                                    binding.tvTopReview.setVisibility(View.VISIBLE);
                                } else {
                                    reviewList.clear();
                                    setReviewAdaper();
                                    binding.tvTopReview.setVisibility(View.GONE);
                                }
                                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (productDetailResponseModel.getComboOffer() != null && productDetailResponseModel.getComboOffer().size() > 0) {
                                    comboOfferList.clear();
                                    binding.tvComboOffer.setVisibility(View.VISIBLE);
                                    comboOfferList.addAll(productDetailResponseModel.getComboOffer());
                                    setComboAdaper();
                                } else {
                                    binding.tvComboOffer.setVisibility(View.GONE);
                                    comboOfferList.clear();
                                    setComboAdaper();
                                }
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (productDetailResponseModel.getProducts().getModifier_list() != null && productDetailResponseModel.getProducts().getModifier_list().size() > 0) {
                                    modifierList.clear();
                                    binding.rlModifier.setVisibility(View.VISIBLE);
                                    modifierList.addAll(productDetailResponseModel.getProducts().getModifier_list());
                                    for (int i = 0; i < modifierList.size(); i++) {
                                        requestHashMap.put(modifierList.get(i).getType(),""+ modifierList.get(i).getType_list().get(0));
                                    }
                                    setModifierAdaper();
                                } else {
                                    binding.rlModifier.setVisibility(View.GONE);
                                    modifierList.clear();
                                    setModifierAdaper();
                                }

                            } else {
                                DialogUtils.showAlertDialogWithSingleButton(instance, productDetailResponseModel.getMessage().isEmpty() ? "getString(R.string.season_expired)" : productDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        finish();
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                                showSnackBar(binding.getRoot(), productDetailResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SingleProductDetailResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void likeDislike() {
        if (isInternetOn(this)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(String.valueOf(merchantId), venueId,
                    String.valueOf(prefManager.getPreference(LOGIN_ID, "")), productId, String.valueOf(modifierId),
                    offerId, costPerProduct, couponCode);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {
                                if (binding.tvAddToWishlist.getText().toString().equals(getString(R.string.add_to_wishlist))) {
                                    binding.tvAddToWishlist.setText(getString(R.string.wishlisted));
                                    binding.tvAddToWishlist.setBackgroundResource(R.drawable.green_filled_rounded_rect);
                                    binding.tvAddToWishlist.setTextColor(getResources().getColor(R.color.color_white));
                                } else {
                                    binding.tvAddToWishlist.setText(getString(R.string.add_to_wishlist));
                                    binding.tvAddToWishlist.setBackgroundResource(R.drawable.grey_filled_rounded_rect);
                                    binding.tvAddToWishlist.setTextColor(getResources().getColor(R.color.app_header_color));
                                }

                                DialogUtils.showAlertDialogWithSingleButton(instance, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                            } else {
                                DialogUtils.showAlertDialogWithSingleButton(instance, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LikeDislikeProductResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void notifyMe() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            final AddressRequestModel requestModel = new AddressRequestModel(prefManager.getPreference(EMAIL, ""),
                    productId, venueId, String.valueOf(merchantId), String.valueOf(modifierId));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotifyMeResponseModel> call = apiInterface.notifyMe(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<NotifyMeResponseModel>() {
                @Override
                public void onResponse(Call<NotifyMeResponseModel> call, Response<NotifyMeResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            NotifyMeResponseModel responseModel = response.body();

                            if (responseModel.isStatus()) {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            }

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<NotifyMeResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void getTotalCartCount() {
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetTotalCartResponseModel> call = apiInterface.getTotalCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<GetTotalCartResponseModel>() {
                @Override
                public void onResponse(Call<GetTotalCartResponseModel> call, Response<GetTotalCartResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            GetTotalCartResponseModel totalCartResponseModel = response.body();
                            if (totalCartResponseModel.isStatus()) {
                                if (totalCartResponseModel.getTotal_carts() > 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                } else binding.tvCartCount.setVisibility(View.GONE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                            // showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetTotalCartResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void setViewPager() {

        binding.viewPager.setAdapter(new SliderAdapterHome(this, imageList, true, -1, false, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }));
        binding.viewPager.setPadding(0, 0, 0, 0);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(0, true);
        binding.viewPager.setPageMargin(10);
        binding.indicator.setupWithViewPager(binding.viewPager, true);
        *//*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 3000);*//*
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            //String productId = data.substring(data.lastIndexOf("/") + 1);
            //String offerId = data.substring(data.lastIndexOf("/")-1 );
            String[] array = data.split("/");
            String offerId = array[array.length - 1];
            String productId = array[array.length - 2];
            *//*Uri contentUri = RecipeContentProvider.CONTENT_URI.buildUpon()
                    .appendPath(recipeId).build();
            showRecipe(contentUri);*//*
            getDeepLinkData(productId, offerId, data);
        }
    }

    private void getDeepLinkData(String productId, String offerId, String data) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SingleProductDetailResponseModel> call = apiInterface.getDeepLinkData(data, productId, offerId);
            call.enqueue(new Callback<SingleProductDetailResponseModel>() {
                @Override
                public void onResponse(Call<SingleProductDetailResponseModel> call, Response<SingleProductDetailResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SingleProductDetailResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {

                                setData(venueDetailResponseModel);

                                if (venueDetailResponseModel.getProducts().getImages() != null && venueDetailResponseModel.getProducts().getImages().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    imageList.clear();
                                    merchantId = venueDetailResponseModel.getProducts().getMerchant_id();
                                    modifierId = venueDetailResponseModel.getProducts().getModifier_id();
                                    costPerProduct = venueDetailResponseModel.getProducts().getSelling_price();
                                    imageList.addAll(venueDetailResponseModel.getProducts().getImages());
                                    setViewPager();

                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    imageList.clear();
                                    setViewPager();
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (venueDetailResponseModel.getProductRating().getReviews() != null &&
                                        venueDetailResponseModel.getProductRating().getReviews().size() > 0) {
                                    reviewList.clear();
                                    reviewList.addAll(venueDetailResponseModel.getProductRating().getReviews());
                                    setReviewAdaper();
                                    binding.tvTopReview.setVisibility(View.VISIBLE);
                                } else {
                                    reviewList.clear();
                                    setReviewAdaper();
                                    binding.tvTopReview.setVisibility(View.GONE);
                                }
                                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (venueDetailResponseModel.getComboOffer() != null && venueDetailResponseModel.getComboOffer().size() > 0) {
                                    comboOfferList.clear();
                                    binding.tvComboOffer.setVisibility(View.VISIBLE);
                                    comboOfferList.addAll(venueDetailResponseModel.getComboOffer());
                                    setComboAdaper();
                                } else {
                                    binding.tvComboOffer.setVisibility(View.GONE);
                                    comboOfferList.clear();
                                    setComboAdaper();
                                }
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (venueDetailResponseModel.getProducts().getModifier_list() != null && venueDetailResponseModel.getProducts().getModifier_list().size() > 0) {
                                    modifierList.clear();
                                    binding.rlModifier.setVisibility(View.VISIBLE);
                                    modifierList.addAll(venueDetailResponseModel.getProducts().getModifier_list());

                                    for (int i = 0; i < modifierList.size(); i++) {
                                        requestHashMap.put(modifierList.get(i).getType(),""+ modifierList.get(i).getType_list().get(0));
                                    }
                                    setModifierAdaper();
                                } else {
                                    binding.rlModifier.setVisibility(View.GONE);
                                    modifierList.clear();
                                    setModifierAdaper();
                                }


                            } else {
                                showSnackBar(binding.getRoot(), venueDetailResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SingleProductDetailResponseModel> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_single_product_details;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String timestamp = extras.getString("timestamp");
                String title = extras.getString("title");
                String message = extras.getString("message");
                String image = extras.getString("image");
                String data = extras.getString("data");

                if (data.equals("DISC_OFFER_NOTIFY")) {
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)) {
                    NotificationModel model = new Gson().fromJson(data, NotificationModel.class);
                    DialogUtils.offerNotificationDialog(this, title, message, image, new Gson().fromJson(data, NotificationModel.class), (position, clickId) -> {
                        Intent topProduct = new Intent(this, SpecialOfferDetailsActivity.class);
                        topProduct.putExtra(PRODUCT_ID, "");
                        topProduct.putExtra(OFFER_ID, "");
                        topProduct.putExtra(SPECIAL_OFFER_ID, String.valueOf(model.getSpecial_offer_id()));
                        startActivity(topProduct);
                    });
                } else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent myOrder = new Intent(this, MyOrderActivity.class);
                        startActivity(myOrder);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSameDayDelivery(SingleProductDetailResponseModel venueDetailResponseModel) {
        if (venueDetailResponseModel.getShippingData() != null) {
            binding.rlSameDayDelivery.setVisibility(View.VISIBLE);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_START_WITH_DATE_NO_YEAR, Locale.getDefault());
                Calendar calendar = Calendar.getInstance();
                Date today = calendar.getTime();  /// Today date

                String endTime = venueDetailResponseModel.getShippingData().getTime();
                Date dateObj1 = sdfSameDayShipping.parse(currentTimeSameDayShipping);
                Date dateObj2 = sdfSameDayShipping.parse(endTime);
                long diff = dateObj2.getTime() - dateObj1.getTime();

                if (diff <= 0) {  /// Time laps
                    String endTimeToday = "23:59";
                    Date dateObjCurrnt = sdfSameDayShipping.parse(currentTimeSameDayShipping);
                    Date dateObjEnd = sdfSameDayShipping.parse(endTimeToday);
                    diff = dateObjEnd.getTime() - dateObjCurrnt.getTime();

                    if (venueDetailResponseModel.getShippingData().getTo_day() > 1) {
                        calendar.add(Calendar.DAY_OF_YEAR, venueDetailResponseModel.getShippingData().getTo_day() + 1);
                        Date tomorrow = calendar.getTime();
                        String tomorrowAsString = dateFormat.format(tomorrow);

                        binding.tvDeliveryDay.setText(tomorrowAsString);

                    } else { binding.tvDeliveryDay.setText(getString(R.string.tomorrow));
                    }
                    if (venueDetailResponseModel.getShippingData().getType() == 1) {
                        binding.tvSameDayDeliveryCost.setText(getString(R.string.next_day_delivery) + " +" + getString(R.string.pound) + venueDetailResponseModel.getShippingData().getAmount());
                    } else
                        binding.tvSameDayDeliveryCost.setText(getString(R.string.next_day_delivery) + " +" + venueDetailResponseModel.getShippingData().getAmount() + getString(R.string.percent));

                } else {
                    if (venueDetailResponseModel.getShippingData().getTo_day() > 1) {
                        calendar.add(Calendar.DAY_OF_YEAR, venueDetailResponseModel.getShippingData().getTo_day());
                        Date tomorrow = calendar.getTime();
                        String tomorrowAsString = dateFormat.format(tomorrow);

                        binding.tvDeliveryDay.setText(tomorrowAsString);
                    } else binding.tvDeliveryDay.setText(getString(R.string.today));

                    if (venueDetailResponseModel.getShippingData().getType() == 1) {
                        binding.tvSameDayDeliveryCost.setText(venueDetailResponseModel.getShippingData().getName() + " +" + getString(R.string.pound) + venueDetailResponseModel.getShippingData().getAmount());
                    } else
                        binding.tvSameDayDeliveryCost.setText(venueDetailResponseModel.getShippingData().getName() + " +" + venueDetailResponseModel.getShippingData().getAmount() + getString(R.string.percent));

                }


                new CountDownTimer(diff, 1000) {
                    public void onTick(long millisUntilFinished) {
                        long millis = millisUntilFinished;
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                        binding.tvExpiryShippingDay.setText(hms);
                    }

                    public void onFinish() {
                        binding.tvExpiryShippingDay.setText("Time up!");
                    }
                }.start();
            } catch (Exception exc) {
                exc.printStackTrace();
                Log.e("TMM", "" + exc);
            }
        } else binding.rlSameDayDelivery.setVisibility(View.GONE);

    }


    private void addToCartBulk() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;
            requestModel = new AddToCartRequestModel
                    (String.valueOf(merchantId), venueId, productId, String.valueOf(modifierId), offerId, "0",
                            ""+ productDetailResponseModel.getProducts().getBulk_selling_qty(), costPerProduct, couponCode, "", "1"*//* + productDetailResponseModel.getProducts().getBulk_selling_qty()*//*, productDetailResponseModel.getProducts().getBulk_selling_price(),
                            prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addToCartBulk(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {

                                binding.tvAddToCartBb.setText(getString(R.string.go_to_bag));
                                binding.tvAddToCartBb.setTextColor(getResources().getColor(R.color.color_white));

                                getTotalCartCount();

                            } else {
                                showSnackBar(binding.getRoot(), cartResponseModel.getMessage());
                            }

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddToCartResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

*/
}