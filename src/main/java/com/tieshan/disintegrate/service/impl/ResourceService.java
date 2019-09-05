package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.dao.ResourceMapper;
import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;
import com.tieshan.disintegrate.service.IResourceService;
//import com.tieshan.disintegrate.util.SessionUtil;
import com.tieshan.disintegrate.util.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @Override
    public List<Menu> menus() {

        List<Menu> menuList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("type", ConStants.RESOURCE_TYPE_MENU);// 菜单类型的资源
//        Long currUid = SessionUtil.getCurrUid();
        params.put("userId", 0);// 只查自己有权限的资源

        List<Resource> resourceList = resourceMapper.getResourceList(params);

        assembleMenu(menuList, resourceList);
        return menuList;
    }

    @Override
    public List<Menu> allMenus() {
        List<Menu> menuList = new ArrayList<Menu>();

        Map<String, Object> params = new HashMap<>();
        List<Resource> resourceList = resourceMapper.getResourceList(params);
        assembleMenu(menuList, resourceList);
        return menuList;
    }

    @Override
    public List<Resource> treeList() {
        Map<String, Object> params = new HashMap<>();

        params.put("userId", 0);// 自查自己有权限的资源
        List<Resource> resourceList = resourceMapper.getResourceList(params);
        List<Resource> list = allResource(resourceList);

        return list;
    }

    private List<Resource> allResource(List<Resource> resourceList) {
        Map<Long, Resource> map = new HashMap<>();
        resourceList.forEach(resource -> map.put(resource.getId(), resource));
        resourceList.forEach(resource -> resource.setResource_pname(resource.getPid() != 0 ? map.get(resource.getPid()).getResource_name() : null));

        List<Resource> list = new ArrayList<>();

        resourceList = new CopyOnWriteArrayList<>(resourceList);
        for (Resource resource : resourceList) {
            if (resource.getPid() == 0) {
                list.add(resource);
                resourceList.remove(resource);
                treeSort(resourceList, list, resource);
            }
        }


        return list;
    }

    @Override
    public int add(Resource resource) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        resource.setId(idWorker.nextId());
        return resourceMapper.save(resource);
    }

    @Override
    public void delete(Long id) {
        resourceMapper.deleteById(id);
    }

    @Override
    public void edit(Resource resource) {
        resourceMapper.update(resource);

    }

    @Override
    public Resource get(Long id) {
        return resourceMapper.getById(id);
    }

    @Override
    public List<Resource> getResourceTree() {
        Map<String, Object> params = new HashMap<>();

        params.put("userId", 0);// 自查自己有权限的资源
        List<Resource> resourceList = resourceMapper.getAllResource(params);
        List<Resource> list = allResource(resourceList);

        return list;
    }

    private void treeSort(List<Resource> resourceList, List<Resource> list, Resource parent) {

        for (Resource resource : resourceList) {
            if (Objects.equals(parent.getId(), resource.getPid())) {
                list.add(resource);
                resourceList.remove(resource);
                treeSort(resourceList, list, resource);
            }
        }
    }

    private void assembleMenu(List<Menu> menuList, List<Resource> resourceList) {
        for (Resource r : resourceList) {
            if (r.getPid() == 0) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(r, menu);
                menu.setName(r.getResource_name());
                List<Menu> children = new ArrayList<>();
                for (Resource r1 : resourceList) {
                    if (Objects.equals(r1.getPid(), r.getId())) {
                        Menu child = new Menu();
                        BeanUtils.copyProperties(r1, child);
                        child.setName(r1.getResource_name());
                        children.add(child);
                    }
                }
                menu.setChildren(children);
                menuList.add(menu);
            }
        }
    }
}
