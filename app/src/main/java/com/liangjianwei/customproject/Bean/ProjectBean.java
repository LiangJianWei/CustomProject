package com.liangjianwei.customproject.Bean;

import java.util.List;

/**
 * Created by LIANGJIANWEI on 2016-1-27
 */
public class ProjectBean {


    /**
     * mockId : 100402
     * projectId : 4028814e5243cf8f0152441ff4c20027
     * projectCode : K2016011506
     * projectName : 课栈项目集合2016011506
     * isVip : 0
     * isNovice : 0
     * annualRate : 9.6
     * rewardRate : 0
     * investmentPeriod : 6
     * investmentAmount : 6900
     * type : 400
     * typeName : 核
     * brandName : 课栈
     * investPercentage : 44
     * status : 201
     * projectBeginDate : 2016-01-15 15:11
     * appRewardRate : 0
     * isAppUserOnly : 1
     * repaymentType : 按月付息，到期还本
     */

    private List<ProjectListEntity> projectList;

    public void setProjectList(List<ProjectListEntity> projectList) {
        this.projectList = projectList;
    }

    public List<ProjectListEntity> getProjectList() {
        return projectList;
    }

    public static class ProjectListEntity {
        private String projectName;

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectName() {
            return projectName;
        }
    }
}
