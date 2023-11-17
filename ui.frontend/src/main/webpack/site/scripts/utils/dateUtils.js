import dayjs from "dayjs";

const dateUtils = {
  getFormatedDate: (date, dateFormat) => {
    const formattedDate =
      date && dateFormat ? dayjs(date).format(dateFormat) : date;
    return formattedDate;
  },
  formatSelectedDate: (date) => {
    date = date.split("/");
    return date[2] + "-" + date[1] + "-" + date[0];
  }
};

export default dateUtils;
