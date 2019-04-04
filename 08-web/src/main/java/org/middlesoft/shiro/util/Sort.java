package org.middlesoft.shiro.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Sort<T> {
    public static void sort(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        log.debug("第一个入参排序：{}", list1);
        log.debug("第二个入参排序：{}", list2);

        for (int i = 0, j = 0; i < list2.size() || j < list1.size(); ) {
            if (list2.size() == 0 || list1.size() == 0) {
                break;
            }
            Integer rp1 = list2.get(i);
            Integer rp2 = list1.get(j);
//            log.debug("rp1:{},rp2:{}",rp1,rp2);
            if (rp1 > rp2) {
                if (j + 1 != list1.size()) {
                    j++;
                } else if (i + 1 != list2.size()) {
                    i++;
                } else {
                    break;
                }
            } else if (rp1 < rp2) {
                if (i + 1 != list2.size()) {
                    i++;
                } else if (j + 1 != list1.size()) {
                    j++;
                } else {
                    break;
                }
            } else {
                list2.remove(i);
                if (i == list2.size() && list2.size() > 0) {
                    i--;
                }
                list1.remove(j);
                if (j == list1.size() && list1.size() > 0) {
                    j--;
                }
            }
        }
    }

    /**
     * 删除集合内的相同对象
     *
     * @param list1
     * @param list2
     * @param c
     */
    public void removeSameObject(List<T> list1, List<T> list2, Comparator<? super T> c) {
        assert list1 == null || list2 == null || c == null;

        Collections.sort(list1, c);
        Collections.sort(list2, c);

        log.debug("第一个入参排序：{}", list1);
        log.debug("第二个入参排序：{}", list2);

        for (int i = 0, j = 0; i < list2.size() || j < list1.size(); ) {
            if (list2.size() == 0 || list1.size() == 0) {
                break;
            }
            T rp1 = list2.get(i);
            T rp2 = list1.get(j);
//            log.debug("rp1:{},rp2:{}",rp1,rp2);
            int ret = c.compare(rp1, rp2);

            if (ret > 0) {
                if (j + 1 != list1.size()) {
                    j++;
                } else if (i + 1 != list2.size()) {
                    i++;
                } else {
                    break;
                }
            } else if (ret < 0) {
                if (i + 1 != list2.size()) {
                    i++;
                } else if (j + 1 != list1.size()) {
                    j++;
                } else {
                    break;
                }
            } else {
                list2.remove(i);
                if (i == list2.size() && list2.size() > 0) {
                    i--;
                }
                list1.remove(j);
                if (j == list1.size() && list1.size() > 0) {
                    j--;
                }
            }
        }
    }

    /**
     * 删除集合内的相同对象
     *
     * @param list1
     * @param list2
     * @param c
     */
    public void removeSameObject(List<Integer> list1, List<T> list2,String intKey, Comparator<? super T> c) {
        assert list1 == null || list2 == null || c == null;

        Collections.sort(list1);

        Collections.sort(list2, c);

        log.debug("第一个入参排序：{}", list1);
        log.debug("第二个入参排序：{}", list2);

        for (int i = 0, j = 0; i < list2.size() || j < list1.size(); ) {
            if (list2.size() == 0 || list1.size() == 0) {
                break;
            }
            JSONObject item = (JSONObject) JSONObject.toJSON(list2.get(i));
            long rp1 = (Long) item.get(intKey);
            Integer rp2 = list1.get(j);
//            log.debug("rp1:{},rp2:{}",rp1,rp2);
//            int ret = c.compare(rp1, rp2);
            int ret = (int) (rp1 - rp2);
            if (ret > 0) {
                if (j + 1 != list1.size()) {
                    j++;
                } else if (i + 1 != list2.size()) {
                    i++;
                } else {
                    break;
                }
            } else if (ret < 0) {
                if (i + 1 != list2.size()) {
                    i++;
                } else if (j + 1 != list1.size()) {
                    j++;
                } else {
                    break;
                }
            } else {
                list2.remove(i);
                if (i == list2.size() && list2.size() > 0) {
                    i--;
                }
                list1.remove(j);
                if (j == list1.size() && list1.size() > 0) {
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(r.nextInt(10));
        }
        System.out.println(list);
        List list1 = new ArrayList();
        r = new Random();
        for (int i = 0; i < 10; i++) {
            list1.add(r.nextInt(10));
        }
        System.out.println(list1);
        sort(list, list1);
        System.out.println(list);
        System.out.println(list1);
    }

}
