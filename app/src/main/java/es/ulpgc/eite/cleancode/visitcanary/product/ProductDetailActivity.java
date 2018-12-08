package es.ulpgc.eite.cleancode.visitcanary.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import es.ulpgc.eite.cleancode.visitcanary.R;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;
import es.ulpgc.eite.cleancode.visitcanary.products.ProductListActivity;


public class ProductDetailActivity
    extends AppCompatActivity implements ProductDetailContract.View {

  public static String TAG = ProductDetailActivity.class.getSimpleName();

  ProductDetailContract.Presenter presenter;

  //private CollapsingToolbarLayout appBarLayout;
  private ActionBar actionBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);

    // Show the Up button in the action bar.
    actionBar = getSupportActionBar();
    //ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //appBarLayout = findViewById(R.id.toolbar_layout);

    // do the setup
    ProductDetailConfigurator.INSTANCE.configure(this);

    // do some work
    presenter.fetchProductDetailData();
  }

  @Override
  public void displayProductDetailData(ProductDetailViewModel viewModel) {
    Log.e(TAG, "displayProductDetailData()");

    // deal with the data
    ProductItem product = viewModel.product;

    if (product != null) {
      if (actionBar != null) {
        actionBar.setTitle(product.content);
      }

      /*
      if (appBarLayout != null) {
        appBarLayout.setTitle(product.content);
      }
      */

      ((TextView) findViewById(R.id.product_detail)).setText(product.details);
      loadImageFromURL(
          (ImageView) findViewById(R.id.product_image),
          product.picture
      );

    }
  }

  private void loadImageFromURL(ImageView imageView, String imageUrl){
    RequestManager reqManager = Glide.with(imageView.getContext());
    RequestBuilder reqBuilder = reqManager.load(imageUrl);
    RequestOptions reqOptions = new RequestOptions();
    reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    reqBuilder.apply(reqOptions);
    reqBuilder.into(imageView);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      navigateUpTo(new Intent(this, ProductListActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}