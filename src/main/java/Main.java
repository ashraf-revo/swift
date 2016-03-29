import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.openstack.OSFactory;

import java.util.List;

/**
 * Created by ashraf on 3/28/2016.
 */
public class Main {
    public static void main(String[] args) {
        String authurl = "https://lon-identity.open.softlayer.com";
        String project = "object_storage_405f38c1_b142_4077_85c5_dcf1cac093c4";
        String projectId = "bb7fb9e3d54e44c69725dc01786140d8";
        String region = "london";
        String userId = "c0c02d35799b4599b308d6eb1342befc";
        String username = "Admin_147e53eeb311f300ebda30070422e6aa889b1629";
        String password = "o9a6eHm9*RpXSt/S";
        String domainId = "a92d167b3b84444abb28f36253b7884c";
        String domainName = "953727";
        Identifier domainIdentifier = Identifier.byName("example-domain");
        OSClient os = OSFactory.builderV3()
                .endpoint(authurl + "/v3")
                .credentials(username, password, null).domainId(domainId).domainName(domainName)
                .authenticate();
        os.useRegion(region);
//        os.objectStorage().containers().create("myContainerName");
        List<? extends SwiftContainer> containers = os.objectStorage().containers().list();
        System.out.println(containers.size());
        containers.forEach(System.out::println);

    }

}
