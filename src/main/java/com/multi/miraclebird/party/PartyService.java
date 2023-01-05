package com.multi.miraclebird.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartyService {
	
	@Autowired
	private PartyDAO partyDao;
	
	@Autowired
	private PartyImgDAO partyImgDao;
	
	@Autowired
	private PartyMemberDAO partyMemberDao;
	
	@Autowired
	private PartyApplicantDAO partyApplicantDao;
	
	
	public void createParty(PartyVO partyVO) {
		partyDao.createParty(partyVO);
		partyMemberDao.createPartyLeader(partyVO);
	}
	
	public boolean isLeader(Integer PartyId, Long userId) {
		boolean isLeader = false;
		
		Long leaderId = (Long) partyDao.findLeaderIdByUserId(userId);
		if (leaderId != null) {
			isLeader = true;
		}
		
		return isLeader;
	}
	
	public Integer findPartyIdByUserId(Long userId) {
		return partyMemberDao.findPartyIdByUserId(userId);
	}

	public PartyVO findPartyByPartyId(int partyId) {
		return partyDao.findPartyByPartyId(partyId);
	}
	
	public void applyJoin(PartyApplicantVO partyApplicantVO) {
		partyApplicantDao.applyJoin(partyApplicantVO);
	}

	public PartyImgVO findPartyImgByPartyId(Integer partyId) {
		return partyImgDao.findPartyImgByPartyId(partyId);
	}

}
