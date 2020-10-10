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

    @Override
    public List<Attribute> findById(Integer element_id){
        List<Attribute> attributeList = attributeMapper.findById(element_id);
        return attributeList;
    }

    @Override
    public void setAttribute(Integer id,String attribute_name,String attribute_value){attributeMapper.setAttribute(id,attribute_name,attribute_value);}

    @Override
    public void addAttribute(Integer element_id,Double pressure,Boolean pressure_state,Double loads,Boolean load_state,Double elevation){attributeMapper.addAttribute(element_id,pressure,pressure_state,loads,load_state,elevation);}

    @Override
    public void deleteAttribute(Integer element_id) {
        attributeMapper.deleteAttribute(element_id);
    }
}
