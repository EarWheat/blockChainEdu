package com.zelu.blockchain.wallet;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.zelu.blockchain.account.Account;
import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Desc: 钱包工具类
 * @Author: 泽露
 * @Date: 2022/8/10 5:25 PM
 * @Version: 1.initial version; 2022/8/10 5:25 PM
 */
public class WalletUtil {

    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

    /**
     * 随机生成钱包
     *
     * @throws Exception
     */
    public static Account createWallet() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.nextBytes(entropy);

        //生成12位助记词
        List<String> str = MnemonicCode.INSTANCE.toMnemonic(entropy);

        //使用助记词生成钱包种子
        byte[] seed = MnemonicCode.toSeed(str, "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        //通过公钥生成钱包地址
        String address = Keys.getAddress(keyPair.getPublicKey());

        return Account.builder()
                .rememberWord(str)
                .address("0x" + address)
                .privateKey(keyPair.getPrivateKey().toString(16))
                .publicKey(keyPair.getPublicKey().toString(16))
                .build();
    }

    /**
     * 创建具有前缀的钱包
     * @param prefix
     * @throws Exception
     */
    public static void createWalletWithPrefix(String prefix) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(30,30,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000));
        for (int i = 0; i < 30; i++) {
            executor.submit(() -> {
                try {
                    while (true){
                        Account account = createWallet();
                        if(account.getPrivateKey() != null && account.getPrivateKey().startsWith(prefix)){
                            System.out.println(JSON.toJSONString(account));
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
