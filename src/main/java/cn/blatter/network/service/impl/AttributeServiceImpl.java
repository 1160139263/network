package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Attribute;
import cn.blatter.network.mapper.AttributeMapper;
import cn.blatter.network.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public List<Attribute> findAll(){
        List<Attribute> attributeList = attributeMapper.findAll();
        return attributeList;
    }
}
