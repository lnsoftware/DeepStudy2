package com.zhiyin.hystrix.http;

import feign.*;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.util.List;

/**
 * Inspired by {@code com.example.retrofit.GitHubClient}
 */
public class MsgNotifyHttpApi {

  interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors2(@Param("owner") String owner, @Param("repo") String repo);
  }

  static class Contributor {
    String login;
    int contributions;
  }

  static class GitHubClientError extends RuntimeException {
    private String message; // parsed from json

    @Override
    public String getMessage() {
      return message;
    }
  }


  static class GitHubErrorDecoder implements ErrorDecoder {

    final Decoder decoder;
    final ErrorDecoder defaultDecoder = new Default();

    GitHubErrorDecoder(Decoder decoder) {
      this.decoder = decoder;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
      try {
        return (Exception) decoder.decode(response, GitHubClientError.class);
      } catch (IOException fallbackToDefault) {
        return defaultDecoder.decode(methodKey, response);
      }
    }
  }
}