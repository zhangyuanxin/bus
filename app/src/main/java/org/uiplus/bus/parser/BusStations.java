package org.uiplus.bus.parser;

import java.util.List;

/**
 * Created by Steven on 17/1/24.
 */

public interface BusStations {

    /**
     * 按照线路名称查询公交路线，如：K301路
     * @param line 线路名称
     * @return 线路集合
     */
    List<String> getBusListByLine(String line);

    /**
     * 按照站点名称查询公交路线，如：泉城广场
     * @param station 站点名称
     * @return 线路集合
     */
    List<String> getBusListByStation(String station);

    /**
     * 按照起始站点和终点站点查询公交路线，如：广州路－宁波路
     * @param origin 起始站点，end 终点站点
     * @return 线路集合
     */
    List<String> getBusListByDestination(String origin, String destination);
}
