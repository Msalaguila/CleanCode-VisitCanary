package es.ulpgc.eite.cleancode.visitcanary.product;

import android.arch.lifecycle.ViewModel;

import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

// include this library dependency in build.gradle file
// implementation 'android.arch.lifecycle:extensions:1.1.1'
public class ProductDetailViewModel extends ViewModel {

  public ProductItem product;
}
