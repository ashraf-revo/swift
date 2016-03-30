import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.payloads.FilePayload;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.openstack.OSFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by ashraf on 3/28/2016.
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException {
        String authurl = "https://lon-identity.open.softlayer.com";
        String project = "object_storage_405f38c1_b142_4077_85c5_dcf1cac093c4";
        String projectId = "bb7fb9e3d54e44c69725dc01786140d8";
        String region = "london";
        String userId = "c0c02d35799b4599b308d6eb1342befc";
        String username = "Admin_147e53eeb311f300ebda30070422e6aa889b1629";
        String password = "o9a6eHm9*RpXSt/S";
        String domainId = "a92d167b3b84444abb28f36253b7884c";
        String domainName = "953727";
        OSClient os = OSFactory.builderV3()
                .endpoint(authurl + "/v3")
                .credentials(username, password, null).domainId(domainId).domainName(domainName)
                .authenticate();
        os.useRegion(region);

        String containers = "lojvex";
        if (!exist(os.objectStorage().containers().list(), containers)) os.objectStorage().containers().create(containers);
        os.objectStorage().objects().put(containers, "file0.png", new FilePayload(new File("/home/revo/Pictures/a.png")));

    }

    static <e> void print(Iterable<e> list) {
        list.forEach(System.out::println);
    }

    static boolean exist(List<? extends SwiftContainer> list, String name) {
        return list.stream().anyMatch(it -> it.getName().equals(name));
    }
}
