package com.zelu.blockchain;

import com.zelu.blockchain.wallet.WalletUtil;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2022/8/10 6:01 PM
 * @Version: 1.initial version; 2022/8/10 6:01 PM
 */
public class WalletTest {

    public static void main(String[] args) throws Exception {
        WalletUtil.createWalletWithPrefix("940106");
    }
}
