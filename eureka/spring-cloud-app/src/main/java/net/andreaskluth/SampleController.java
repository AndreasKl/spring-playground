package net.andreaskluth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  private DiscoveryClient client;

  @Autowired
  public SampleController(DiscoveryClient client) {
    this.client = client;
  }

  @RequestMapping("/sample")
  public String helloWorld() {
    StringBuffer sb = new StringBuffer();
    for (String service : client.getServices()) {
      for (ServiceInstance instance : client.getInstances(service)) {
        sb.append("->" + instance.getUri() + " -> " + instance.getServiceId() + ";");
      }
    }
    return sb.toString();
  }

}
