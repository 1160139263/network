package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.ComponentMapper;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.mapper.PipeMapper;
import cn.blatter.network.mapper.ProjectsMapper;
import cn.blatter.network.service.*;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/9 15:13
 */
@Transactional
@Service("projectsService")
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	private ProjectsMapper projectsMapper;

	@Autowired
	private ComponentService componentService;

	@Autowired
	private ElementService elementService;

	@Autowired
	private ConnectionService connectionService;

	@Autowired
	private NodeMapper nodeMapper;

	@Autowired
	private PipeMapper pipeMapper;

	static String path = "src/main/resources";

	/**
	 * 分页查询
	 * @param info
	 * @return
	 */
	@Override
	public List<Projects> pageQuery(PageInfo info) {
		List<Projects> projectsList = projectsMapper.pageQuery(info.getPageSize() * (info.getPageNumber()-1), info.getPageSize());
		return projectsList;
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public Projects queryOne(Integer id) {
		Projects projects = projectsMapper.queryById(id);
		return projects;
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<Projects> findAll() {
		return projectsMapper.findAll();
	}

	@Override
	public Integer insertOne(Projects projects) {
		try{
			String s = "/Models/" + projects.getAuthor()+ "-" + projects.getInfo() + ".xml";
			File file = new File(path + s);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
			BufferedWriter br = new BufferedWriter(out);
			String str = projects.getModel();
			if(str.equals("")) {
				str = "<mxGraphModel><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/></root></mxGraphModel>";
			}
			br.write(str);
			br.flush();
			br.close();
			projects.setModel(s);
			System.out.println("新建工程："+projects.getModel());
			projectsMapper.insertProjects(projects);
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			System.out.println("生成nodes...");
			List<Node> nodeList = xmlUtil.generateNodes(path + s, projects.getPid());
			System.out.println(nodeList.toString());
			for(Node node : nodeList) {
				nodeMapper.insertNode(node);
			}
			System.out.println("生成pipes...");
			List<Pipe> pipeList = xmlUtil.generatePipes(path + s, projects.getPid(), nodeList);
			System.out.println(pipeList.toString());
			for(Pipe pipe : pipeList) {
				pipeMapper.insertPipe(pipe);
			}
			System.out.println("生成完成");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return projects.getPid();
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteProject(Integer id) {
		Integer result = projectsMapper.deleteById(id);
		return result;
	}

	/**
	 * 根据项目id获取项目气体信息
	 * @param id
	 * @return
	 */
	@Override
	public List<GasProperty> getProjectComponent(Integer id) {
		Projects projects = projectsMapper.queryById(id);
		Integer comId = projects.getComId();
		List<GasProperty> properties = componentService.queryComponentProperty(comId);
		return properties;
	}
}
