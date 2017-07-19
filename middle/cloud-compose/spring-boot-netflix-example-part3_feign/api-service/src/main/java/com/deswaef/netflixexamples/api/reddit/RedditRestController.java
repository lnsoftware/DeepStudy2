package com.deswaef.netflixexamples.api.reddit;

import com.deswaef.netflixexamples.api.reddit.client.RedditResource;
import com.deswaef.netflixexamples.api.reddit.model.RedditResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/reddit")
public class RedditRestController {

    @Autowired
    private RedditResource redditResource;

    @RequestMapping(method = GET)
    public RedditResponse get() {
        return redditResource.posts();
    }

}
