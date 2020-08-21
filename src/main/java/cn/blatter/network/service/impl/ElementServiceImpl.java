package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Element;
import cn.blatter.network.mapper.ElementMapper;
import cn.blatter.network.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("elementService")
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementMapper elementMapper;

    @Override
    public List<Element> findAll() {
        List<Element> elementList = elementMapper.findAll();
        return elementList;
    }

    @Override
    public Element findById(Integer id) {
        Element element = elementMapper.findById(id);
        return element;
    }

    @Override
    public void setElement(Integer id,String name) {
        elementMapper.setElement(id,name);
    }

    @Override
    public void deleteElement(Integer id) {
        elementMapper.deleteElement(id);
    }

    @Override
    public void addElement(Integer id,String name,String path) {
        elementMapper.addElement(id,name,path);
    }
}
