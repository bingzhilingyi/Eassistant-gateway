/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.controller;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.file.FileUtil;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

/**
 * 层级树
 * @author huangyue
 * @date 2018年5月31日 下午8:58:07
 * @ClassName QaTreeController
 */
@RestController
@RequestMapping(path="/tree")
public class QaTreeController extends QaBaseController{

	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	
	@Value("${FILEPATH.UPLOAD}")
    private String FILE_UPLOAD_PATH; //文件上传地址
	
	@Value("${FILEPATH.DOWNLOAD}")
    private String FILE_DOWNLOAD_PATH; //文件下载地址
	
	/**
	 * 查找所有root节点
	 * @author huangyue
	 * @date 2018年6月1日 上午9:12:57
	 * @return
	 */
	@GetMapping(path="/findRoot")
	public QaBaseTransfer findRoot() {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findRoot();
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 根据父id查找子集
	 * @author huangyue
	 * @date 2018年6月1日 上午9:15:30
	 * @param parentId
	 * @return
	 */
	@GetMapping(path="/findByParentId/{parentId}")
	public QaBaseTransfer findByParentId(@PathVariable(value="parentId") Integer parentId) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findByParentId(parentId);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 通过id查找节点
	 * @author huangyue
	 * @date 2018年6月1日 上午9:22:12
	 * @param id
	 * @return
	 */
	@GetMapping(path="/findById/{Id}")
	public QaGenericBaseTransfer<QaTreeDto> findById(@PathVariable(value="Id") Integer id) {
		//创建返回对象
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			dto = qaTreeService.findById(id);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 根据title精确查找
	 * @author huangyue
	 * @date 2018年5月31日 下午2:40:14
	 * @param token
	 * @param title
	 * @return
	 */
	@GetMapping(path="/findByTitle")
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(@RequestParam(value="title") String title) {
		//创建返回对象
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			dto = qaTreeService.findByTitle(title);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 通过标题模糊查询
	 * @author huangyue
	 * @date 2018年5月31日 上午11:29:54
	 * @param token
	 * @param title
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(path="/findPagedByTitleLike")
	public QaGenericPagedTransfer<QaTreeSimpleDto> findByTitleLike(
			@RequestParam(value="title") String title,
			@RequestParam(value="page") Integer page,
			@RequestParam(value="size") Integer size) {
		//创建返回对象
		QaGenericPagedTransfer<QaTreeSimpleDto> dto = new QaGenericPagedTransfer<QaTreeSimpleDto>();
		try {
			dto = qaTreeService.findPagedByTitleLike(title, page, size);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 保存节点信息
	 * @author huangyue
	 * @date 2018年5月28日 下午2:06:34
	 * @param value
	 * @param node
	 * @return
	 */
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="node") String node){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.save(node);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 更新节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:38
	 * @param token
	 * @param node
	 * @return
	 */
	@PutMapping(path="/update")
	public QaBaseTransfer update(@RequestParam(value="node") String node){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.update(node);
		} catch (QaTreeException e) {
			returnError(e, dto);
		} 
		return dto;
	}
	
	/**
	 * 删除节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:45
	 * @param token
	 * @param id
	 * @return
	 */
	@DeleteMapping(path="/delete")
	public QaBaseTransfer delete(@RequestParam(value="id") Integer id){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.delete(id);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	@PostMapping(path="/upload")
	public QaBaseTransfer upload(@RequestBody MultipartFile image,HttpServletRequest request) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","上传成功！");
		//创建独一无二的文件名
        String fileName = image.getOriginalFilename();
        String[] filenames = fileName.split("\\.");
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + "."+filenames[filenames.length-1];
        //生成上传与下载路径
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        String uploadpath = FILE_UPLOAD_PATH+year+"/"+month+"/";
        String downloadpath = FILE_DOWNLOAD_PATH+year+"/"+month+"/"+fileName;
        
        try {
            FileUtil.uploadFile(image.getBytes(), uploadpath, fileName);
            dto.setContent(downloadpath);
        } catch (Exception e) {
        	returnError(e, dto);
        }
        //返回json
        return dto;
	    
	}
}
