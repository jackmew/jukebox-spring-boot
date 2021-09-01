package com.zestlifia.jukebox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

@Slf4j
@RestController
public class PingController {

    @GetMapping("/ping")
    String jukebox() {
        return "1. /ping/url/{url} 2. /ping/local 3. /ping/host/{hostname}";
    }

    @GetMapping("/ping/url/{url}")
    String jukeboxPing(@PathVariable String url) {
        boolean isPing = pingURL(url, 3000);
        if (isPing) {
            return "ping success";
        } else {
            return "ping failed";
        }
    }
    @GetMapping("/ping/local")
    String pingLocal() {
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();

            String ipContent = "Your current IP address : " + ip;
            String hostnameContent = "Your current Hostname : " + hostname;

            log.info(ipContent);
            log.info(hostnameContent);

            return ipContent + " - " + hostnameContent;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "UnknownHostException";
        }
    }
    @GetMapping("/ping/host/{hostname}")
    String pingHostname(@PathVariable String hostname) {
        try{
            InetAddress ip=InetAddress.getByName(hostname);

            String hostContent = "Host Name: "+ip.getHostName();
            String ipContent = "IP Address: "+ip.getHostAddress();

            log.info(hostContent);
            log.info(ipContent);

            return hostContent + " - " + ipContent;
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public static boolean pingURL(String url, int timeout) {
//        url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.
        url = "https://"+url;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }
    }
}
