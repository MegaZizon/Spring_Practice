package org.zerock.sessioncookies.service;

import org.modelmapper.ModelMapper;
import org.zerock.sessioncookies.dao.MemberDAO;
import org.zerock.sessioncookies.domain.MemberVO;
import org.zerock.sessioncookies.dto.MemberDTO;
import org.zerock.sessioncookies.util.MapperUtil;

public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDTO login(String mid, String mpw) throws Exception {

        MemberVO vo = dao.geWithPassword(mid,mpw);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }

    public void updateUuid(String mid,String uuid) throws Exception {
        dao.updateUuid(mid,uuid);
    }

    public MemberDTO getByUUID(String uuid) throws Exception {
        MemberVO vo = dao.selectUUID(uuid);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }
}
