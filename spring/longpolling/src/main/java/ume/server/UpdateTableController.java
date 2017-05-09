package ume.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ume.TableChangeMeta;

/**
 * Created by hg on 2017/5/17.
 */
@RestController
public class UpdateTableController {

    @RequestMapping(value = "/put/{data}", method = RequestMethod.GET)
    public String put(@PathVariable("data") String data){
        TableChangeMeta meta = new TableChangeMeta();
        meta.setTableName(data);
        MessageQueue.queue.add(meta);
       return data;
    }

}
