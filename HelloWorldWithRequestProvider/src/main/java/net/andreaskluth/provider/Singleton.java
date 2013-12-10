package net.andreaskluth.provider;

import javax.inject.Inject;
import javax.inject.Provider;

public class Singleton {

  @Inject
  Provider<PrototypeBean> someRequestBean;

}
