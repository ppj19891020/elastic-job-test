import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author xiangnan
 * @date 2021年11月11日 8:26 下午
 */
public class Test {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString("127.0.0.1:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
//                        .namespace("base")
                        .build();
        client.start();

//        String path = "/anduin-elastic-job-test";
//        client.delete().deletingChildrenIfNeeded().forPath(path);
//        client.delete().forPath(path);

//        client.delete().forPath("/demo-elastic-job/demoJob/status");
//        client.create().creatingParentsIfNeeded().forPath("/demo-elastic-job/demoJob/status","false".getBytes());

//        client.delete().deletingChildrenIfNeeded().forPath("/demo-elastic-job");


        String json = new String(client.getData().forPath("/demo-elastic-job/demoJob/config"));
        JSONObject jsonObject = JSON.parseObject(json);
        Boolean bol = (Boolean)jsonObject.get("disabled");
        jsonObject.put("disabled",false);
        client.setData().forPath("/demo-elastic-job/demoJob/config",jsonObject.toJSONString().getBytes());


        client.setData().forPath("/demo-elastic-job/demoJob/servers/192.168.24.100","".getBytes());
    }

}
