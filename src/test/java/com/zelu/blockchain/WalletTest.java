package com.zelu.blockchain;

import com.zelu.blockchain.wallet.WalletUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2022/8/10 6:01 PM
 * @Version: 1.initial version; 2022/8/10 6:01 PM
 */
public class WalletTest {

    public static void main(String[] args) throws Exception {
        WalletUtil.createWalletWithPrefix("9416");
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("1111");
//        list.add("2");
//        list.add("3");
//        for (int i = 0; i < 10000; i++) {
//            list.stream().filter(s -> {
//                return s.startsWith("1");
//            }).forEach(System.out::println);
//            System.out.println("======");
//        }
    }
}
