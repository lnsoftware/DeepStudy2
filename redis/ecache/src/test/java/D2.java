import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.*;

/**
 * Created by wangqinghui on 2016/11/17.
 */
public class D2 {

    public static void main(String[] args) {

        Configuration cacheManagerConfig = new Configuration()
                .diskStore(new DiskStoreConfiguration()
                        .path("/path/to/store/data"));
        CacheConfiguration cacheConfig = new CacheConfiguration()
                .name("my-cache")
                .maxBytesLocalHeap(16, MemoryUnit.MEGABYTES)
                .maxBytesLocalOffHeap(256, MemoryUnit.MEGABYTES)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));

        cacheManagerConfig.addCache(cacheConfig);

        CacheManager cacheManager = new CacheManager(cacheManagerConfig);
        Ehcache myCache = cacheManager.getEhcache("my-cache");

    }
}
