package com.zelu.blockchain.account;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2022/8/10 6:15 PM
 * @Version: 1.initial version; 2022/8/10 6:15 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    /**
     * 私钥
     */
    @JSONField(name = "privateKey")
    public String privateKey;

    /**
     * 助记词
     */
    @JSONField(name = "rememberWord")
    public List<String> rememberWord;

    /**
     * 公钥
     */
    @JSONField(name = "publicKey")
    public String publicKey;

    /**
     * 钱包地址
     */
    @JSONField(name = "address")
    public String address;
}
