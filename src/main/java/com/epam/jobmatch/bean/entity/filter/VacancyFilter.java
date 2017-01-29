package com.epam.jobmatch.bean.entity.filter;


/**
 * Describes vacancy filter parameters for
 * searching necessary vacancies
 */
public class VacancyFilter {

    private String searchString;
    private int page;
    private String sort;
    private int minExperienceFilter;
    private int maxExperienceFilter;
    private int salaryFilter;
    private String countryFilter;
    private String cityFilter;

    public String getSearchString() {
        return searchString;
    }
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public int getMinExperienceFilter() {
        return minExperienceFilter;
    }
    public void setMinExperienceFilter(int minExperienceFilter) {
        this.minExperienceFilter = minExperienceFilter;
    }
    public int getMaxExperienceFilter() {
        return maxExperienceFilter;
    }
    public void setMaxExperienceFilter(int maxExperienceFilter) {
        this.maxExperienceFilter = maxExperienceFilter;
    }
    public int getSalaryFilter() {
        return salaryFilter;
    }
    public void setSalaryFilter(int salaryFilter) {
        this.salaryFilter = salaryFilter;
    }
    public String getCountryFilter() {
        return countryFilter;
    }
    public void setCountryFilter(String countryFilter) {
        this.countryFilter = countryFilter;
    }
    public String getCityFilter() {
        return cityFilter;
    }
    public void setCityFilter(String cityFilter) {
        this.cityFilter = cityFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VacancyFilter that = (VacancyFilter) o;

        if (page != that.page) return false;
        if (minExperienceFilter != that.minExperienceFilter) return false;
        if (maxExperienceFilter != that.maxExperienceFilter) return false;
        if (salaryFilter != that.salaryFilter) return false;
        if (searchString != null ? !searchString.equals(that.searchString) : that.searchString != null) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;
        if (countryFilter != null ? !countryFilter.equals(that.countryFilter) : that.countryFilter != null)
            return false;
        return cityFilter != null ? cityFilter.equals(that.cityFilter) : that.cityFilter == null;

    }

    @Override
    public int hashCode() {
        int result = searchString != null ? searchString.hashCode() : 0;
        result = 31 * result + page;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + minExperienceFilter;
        result = 31 * result + maxExperienceFilter;
        result = 31 * result + salaryFilter;
        result = 31 * result + (countryFilter != null ? countryFilter.hashCode() : 0);
        result = 31 * result + (cityFilter != null ? cityFilter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                " searchString='" + searchString + '\'' +
                ", page='" + page + '\'' +
                ", sort='" + sort + '\'' +
                ", minExperienceFilter='" + minExperienceFilter + '\'' +
                ", maxExperienceFilter='" + maxExperienceFilter + '\'' +
                ", salaryFilter='" + salaryFilter + '\'' +
                ", countryFilter='" + countryFilter + '\'' +
                ", cityFilter='" + cityFilter + '\'';
    }
}
