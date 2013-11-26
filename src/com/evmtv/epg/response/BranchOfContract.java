package com.evmtv.epg.response;

import java.util.List;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.utils.DateUtils;

/**
 * 获取分公司合同
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 */
public class BranchOfContract {
	/**
	 * 获取分公司合同
	 * @param user
	 * @param iContract
	 * @param iAdv
	 * @return
	 */
	public List<TContract> getContractByBranch(TUser user, IContract iContract, IAdv iAdv){
		TContract contract = new TContract();
		Long branchid = user.getFbranchid();
		contract.setFbranchid(branchid);
		contract.setTemp(DateUtils.formatDate());
		List<TContract> contracts = iContract.query(contract);
		// 所有广告位
		List<TAdv> advs = iAdv.query(branchid);
		for (int i = 0, len = contracts.size(); i < len; i++) {
			TContract c = contracts.get(i);
			for (TAdv adv : advs) {
				if (c.getFadvlocid().toString().equals(adv.getId().toString())) {
					c.setTemp(adv.getFtype());
					contracts.set(i, c);
				}
			}
		}
		
		return contracts;
	}
	/**
	 * 获取当前用户分公司索引
	 * @param user
	 * @return
	 */
	@Deprecated
	public static Long getCurrentUserFbranchid(TUser user){
		/*Long branchid = null;
		if(!"1".equals(user.getFbranchid().toString())){
			branchid = user.getFbranchid();
		}*/
		return user.getFbranchid();
	}
}
