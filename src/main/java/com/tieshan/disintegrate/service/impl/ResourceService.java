package com.tieshan.disintegrate.service.impl;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.dao.ResourceMapper;
import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IResourceService;
//import com.tieshan.disintegrate.util.SessionUtil;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/30 18:11
 * @version: 1.0
 * @modified By:
 */
@Service
public class ResourceService implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private TokenService tokenService;

    //    @Override
//    public List<Menu> menus() {
//
//        List<Menu> menuList = new ArrayList<>();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("type", ConStants.RESOURCE_TYPE_MENU);// 菜单类型的资源
////        Long currUid = SessionUtil.getCurrUid();
//        params.put("userId", 0);// 只查自己有权限的资源
//
//        List<Resource> resourceList = resourceMapper.getResourceList(params);
//
//        assembleMenu(menuList, resourceList);
//        return menuList;
//    }
    @Override
    public List<Menu> departTree(String depart_id, HttpServletRequest request) {   //调用的方法入口
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        List<Menu> bodyList = getResourceByDepartId(depart_id, sysUser.getCompany_code());
        Menu m = bodyList.remove(0);
        List<Menu> rootList = new ArrayList<>();
        rootList.add(m);
        if (bodyList != null && !bodyList.isEmpty()) {
            //声明一个map，用来过滤已操作过的数据
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(menu -> getChild(menu, bodyList, map));
            return rootList;
        }
        return null;
    }

    private static void getChild(Menu menu, List<Menu> bodyList, Map<String, Object> map) {
        List<Menu> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getId()))
                .filter(c -> c.getPid().equals(menu.getId()))
                .forEach(c -> {
                    map.put(c.getId().toString(), c.getPid());
                    getChild(c, bodyList, map);
                    childList.add(c);
                });
        menu.setChildren(childList);

    }

    @Override
    public List<Menu> departMenus(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        List<Menu> bodyList = resourceMapper.departTree(sysUser.getDepart_id() + "", sysUser.getCompany_code());
        Menu m = bodyList.remove(0);
        List<Menu> rootList = new ArrayList<>();
        rootList.add(m);
        if (bodyList != null && !bodyList.isEmpty()) {
            //声明一个map，用来过滤已操作过的数据
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(menu -> getChild(menu, bodyList, map));
            return rootList;
        }
        return null;


//        List<Menu> resourceList = resourceMapper.departTree(depart_id);
//        List<Menu> list = allResource(resourceList);
//
//        return list;
    }

    private List<Menu> allResource(List<Menu> resourceList) {
        Map<Long, Menu> map = new HashMap<>();
        resourceList.forEach(menu -> map.put(menu.getId(), menu));
        resourceList.forEach(menu -> menu.setResource_pname(menu.getPid() != 0L ?
                !PubMethod.isEmpty(map.get(menu.getPid())) ? map.get(menu.getPid()).getResource_name() : null : null));

        List<Menu> list = new ArrayList<>();

        resourceList = new CopyOnWriteArrayList<>(resourceList);
        for (Menu menu : resourceList) {
            if (menu.getPid() == 0) {
                list.add(menu);
                resourceList.remove(menu);
                treeSort(resourceList, list, menu);
            }
        }


        return list;
    }

    @Override
    public int add(Resource resource, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        IdWorker idWorker = new IdWorker(1, 1, 1);
        resource.setId(idWorker.nextId() + "");
        resource.setCompany_code(sysUser.getCompany_code());
        return resourceMapper.save(resource);
    }


    @Override
    public List<Menu> getResourceTree(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        List<Menu> bodyList = resourceMapper.getAllResource(sysUser.getCompany_code());
//        List<Menu> list = allResource(resourceList);
        Menu m = bodyList.remove(0);
        List<Menu> rootList = new ArrayList<>();
        rootList.add(m);
        if (bodyList != null && !bodyList.isEmpty()) {
            //声明一个map，用来过滤已操作过的数据
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(menu -> getChild(menu, bodyList, map));
            return rootList;
        }
        return null;
    }

    @Override
    @Transactional
    public int updateDR(String department_id, String resource_ids) {
        resourceMapper.delDepartment_Resource(department_id);
        IdWorker idWorker = new IdWorker(1, 1, 1);
        String[] ids = resource_ids.split(",");
        List<String> resource_idList = Arrays.asList(ids);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!PubMethod.isEmpty(resource_idList)) {
            for (String id : resource_idList) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", idWorker.nextId());
                map.put("department_id", department_id);
                map.put("resource_id", id);
                mapList.add(map);
            }
            int num = resourceMapper.insertDepartment_Resource(mapList);
            return num;
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> getNode(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return resourceMapper.getNode(sysUser.getCompany_code());
    }

    @Override
    public Map<String, Object> getNodeById(String id) {
        return resourceMapper.getNodeById(id);
    }

    @Override
    public int updateRS(Resource resource) {
        return resourceMapper.updateRS(resource);
    }

    // @Override
    private List<Menu> getResourceByDepartId(String department_id, String company_code) {
        List<Map<String, Object>> mapList = resourceMapper.getDepartment_Resource(department_id);
        List<Menu> resourceList = resourceMapper.getAllResource(company_code);

        if (!PubMethod.isEmpty(mapList)) {
            Map<String, Map<String, Object>> drMap = new HashMap<>();
            mapList.stream().forEach(map -> {
                drMap.put(map.get("resource_id").toString(), map);
            });
            resourceList.stream().forEach(resource -> {
                if (!PubMethod.isEmpty(drMap.get(resource.getId() + ""))) {
                    resource.setIsHaving(ConStants.YES_HAVING);
                }
            });
        }
        return resourceList;
    }

    private void treeSort(List<Menu> resourceList, List<Menu> list, Menu parent) {

        for (Menu menu : resourceList) {
            if (Objects.equals(parent.getId(), menu.getPid())) {
                list.add(menu);
                resourceList.remove(menu);
                treeSort(resourceList, list, menu);
            }
        }
    }

//    private void assembleMenu(List<Menu> menuList, List<Resource> resourceList) {
//        for (Resource r : resourceList) {
//            if (r.getPid() == 0) {
//                Menu menu = new Menu();
//                BeanUtils.copyProperties(r, menu);
//                menu.setResource_name(r.getResource_name());
//                List<Menu> children = new ArrayList<>();
//                for (Resource r1 : resourceList) {
//                    if (Objects.equals(r1.getPid(), r.getId())) {
//                        Menu child = new Menu();
//                        BeanUtils.copyProperties(r1, child);
//                        child.setResource_name(r1.getResource_name());
//                        children.add(child);
//                    }
//                }
//                menu.setChildren(children);
//                menuList.add(menu);
//            }
//        }
//    }
}
