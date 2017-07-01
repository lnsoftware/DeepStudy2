package basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2017/6/29.
 */
@Service
public class DemoService {

    @Autowired
    private BaseService baseService;

    public DemoService( ){

    }
}
