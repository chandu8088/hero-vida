import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import InputField from "../../form/InputField/InputField";
import PhoneNumber from "../../form/PhoneNumber/PhoneNumber";
import { useForm } from "react-hook-form";
import Dropdown from "../../form/Dropdown/Dropdown";
import axios from "axios";
import appUtils from "../../../../site/scripts/utils/appUtils";
import { setSpinnerActionDispatcher } from "../../../store/spinner/spinnerActions";
import FileBase64 from "react-file-base64";
import { showNotificationDispatcher } from "../../../store/notification/notificationActions";
import CONSTANT from "../../../../site/scripts/constant";

const ComplainForm = (props) => {
  const [selectSubCategory, setselectedSubCategory] = useState();
  const defaultCountryCode = appUtils.getConfig("defaultCountryCode");
  const codeList = appUtils.getConfig("countryCodes");
  const [sessionToken, setsessionToken] = useState();
  const [description, setDescription] = useState();
  const [fileName, setFilename] = useState("");
  const [fileType, setFiletype] = useState("");
  const [fileBase64data, SetFileBase64Data] = useState("");
  const {
    bookingTitle,
    systemfield,
    categoryfield,
    subcategoryfield,
    casedescField,
    firstNameField,
    casesubjectField,
    attachmentfield,
    phoneNumberField,
    emailField,
    nextBtn,
    errormsg
  } = props.config;

  var defaultsyname = [
    {
      label: systemfield.validationRules.required.message,
      value: ""
    }
  ];
  const [systemname, setSystemname] = useState(defaultsyname);
  const [selectSystem, setSelelctedSystem] = useState();
  var defaultcategory = [
    {
      label: categoryfield.validationRules.required.message,
      value: ""
    }
  ];
  const [successStatus, setsuccessStatus] = useState(false);
  const [category, setCategory] = useState(defaultcategory);
  const [selectCategory, setselectedCategory] = useState();
  const [catdata, setCatdata] = useState();
  const [subcatdata, setSubCatatdata] = useState();
  const [caseId, setCaseId] = useState();

  var defaultsubcategory = [
    {
      label: subcategoryfield.validationRules.required.message,
      value: ""
    }
  ];
  const [subcategory, setSubCategory] = useState(defaultsubcategory);

  const {
    register,
    handleSubmit,
    setValue,
    setError,
    clearErrors,
    formState: { errors }
  } = useForm({
    mode: "onSubmit",
    reValidateMode: "onChange"
  });

  async function getsytemTypelist() {
    setSpinnerActionDispatcher(true);
    await axios
      .get(appUtils.getAPIUrl("internalUserUrl"))
      .then(async (response) => {
        defaultsyname.push(...response?.data?.result?.values);
        setSystemname(defaultsyname);
        setSpinnerActionDispatcher(false);
        setCatdata(response?.data?.category);
        setSubCatatdata(response?.data?.subcategory);
        setsessionToken(response?.data?.token);
      })
      .catch((error) => {
        setSpinnerActionDispatcher(false);
      });
  }

  const getcategory = async (selectSystem) => {
    setSpinnerActionDispatcher(true);

    const catindex = catdata?.controllerValues[selectSystem];
    var result = await catdata?.values.filter((val, key) => {
      return val.validFor[0] == catindex;
    });
    defaultcategory.push(...result);
    setCategory(defaultcategory);
    setSpinnerActionDispatcher(false);
  };

  const getsubcategory = async (selectCategory) => {
    setSpinnerActionDispatcher(true);
    const subcatindex = subcatdata?.controllerValues[selectCategory];
    var result = await subcatdata?.values.filter((val, key) => {
      return val.validFor[0] == subcatindex;
    });
    defaultsubcategory.push(...result);
    setSubCategory(defaultsubcategory);
    setSpinnerActionDispatcher(false);
  };

  const submitCompainform = async (formData) => {
    setSpinnerActionDispatcher(true);
    await axios
      .post(appUtils.getAPIUrl("internalUserUrl"), formData)
      .then((response) => {
        if (response?.data?.status == 200) {
          setCaseId(response?.data?.id);
          setSpinnerActionDispatcher(false);
          setsuccessStatus(true);
          setValue("Contact_Email_Address", " ");
          setValue("Phone", " ");
          setValue("Subject", " ");
          setValue("fileExtension", " ");
          setValue("name", " ");
        } else {
          showNotificationDispatcher({
            title: errormsg.failure,
            type: CONSTANT.NOTIFICATION_TYPES.ERROR,
            isVisible: true
          });
          setSpinnerActionDispatcher(false);
          setsuccessStatus(false);
        }
      })
      .catch((error) => {
        setsuccessStatus(false);
        showNotificationDispatcher({
          title: errormsg.failure,
          type: CONSTANT.NOTIFICATION_TYPES.ERROR,
          isVisible: true
        });
        setSpinnerActionDispatcher(false);
      });
  };

  const handleDropdownChange = async (name, value) => {
    if (value !== "") {
      setSelelctedSystem(value);
      getcategory(value);
    }
  };
  const handleFormSubmit = async (formData) => {
    formData.base64 = fileBase64data;
    formData.fileExtension = fileType;
    formData.fileName = fileName;
    formData.recordType = "Internal Complaints/Queries";
    formData.Description = description;
    formData.Priority = "Low";
    formData.token = sessionToken;
    formData.Status = "unassigned";
    submitCompainform(formData);
  };

  const handlecategoryDropdownChange = (name, value) => {
    if (value !== "") {
      setselectedCategory(value);
      getsubcategory(value);
    }
  };

  const handleSubCategoryDropdownChange = (name, value) => {
    if (value !== "") {
      setselectedSubCategory(value);
    }
  };

  const handleInputChange = (fieldname, value) => {
    if (value === "") {
      setError(fieldname, {
        type: "required"
      });
    }
  };

  const handleFileinput = (e) => {
    setFilename(e.file.name);
    setFiletype(e.file.type);
    SetFileBase64Data(e.base64.split("base64,")[1]);
  };

  useEffect(() => {
    getsytemTypelist();
  }, []);

  return (
    <>
      {successStatus ? (
        <div className="vida-internal-user-complain-form__title">
          <div className="vida-internal-user-complain-form__title">
            <h1>{errormsg.congratsmsg}</h1>
          </div>
          <div>
            <h4>
              {errormsg.casemsg} {caseId}
            </h4>
            <p className="vida-internal-user-complain-form__confirmation-msg">
              {errormsg.success}
            </p>
          </div>
        </div>
      ) : (
        <>
          <h1 className="vida-internal-user-complain-form__title">
            {bookingTitle}
          </h1>
          <form
            className="vida-internal-user-complain-form"
            onSubmit={handleSubmit((formData, event) =>
              handleFormSubmit(formData, event)
            )}
          >
            <Dropdown
              name="Types"
              label={systemfield.label}
              options={systemname}
              value=""
              setValue={setValue}
              onChangeHandler={handleDropdownChange}
              errors={errors}
              validationRules={systemfield.validationRules}
              clearErrors={clearErrors}
              register={register}
            />

            <Dropdown
              name="Complaint_Category"
              label={categoryfield.label}
              options={category}
              value=""
              setValue={setValue}
              onChangeHandler={handlecategoryDropdownChange}
              errors={errors}
              validationRules={categoryfield.validationRules}
              clearErrors={clearErrors}
              register={register}
            />
            <Dropdown
              name="Aggregates"
              label={subcategoryfield.label}
              options={subcategory}
              value=""
              setValue={setValue}
              onChangeHandler={handleSubCategoryDropdownChange}
              errors={errors}
              validationRules={subcategoryfield.validationRules}
              clearErrors={clearErrors}
              register={register}
            />
            <>
              <InputField
                name="name"
                label={firstNameField.label}
                placeholder={firstNameField.placeholder}
                value=""
                validationRules={firstNameField.validationRules}
                register={register}
                errors={errors}
                checkNameFormat
                setValue={setValue}
                onChangeHandler={handleInputChange}
              />

              <InputField
                name="Contact_Email_Address"
                label={emailField.label}
                infoLabel=""
                placeholder={emailField.placeholder}
                validationRules={emailField.validationRules}
                register={register}
                errors={errors}
                checkEmailFormat
                onChangeHandler={handleInputChange}
                value=""
                setValue={setValue}
              />
              <PhoneNumber
                label={phoneNumberField.label}
                fieldNames={{
                  inputFieldName: "Phone",
                  selectFieldName: "countryCode"
                }}
                placeholder={phoneNumberField.placeholder}
                options={codeList}
                values={{
                  code: defaultCountryCode || "",
                  number: ""
                }}
                validationRules={phoneNumberField.validationRules}
                register={register}
                errors={errors}
                setValue={setValue}
                maxLength={phoneNumberField.validationRules.maxLength.value}
              />
            </>
            <InputField
              name="Subject"
              label={casesubjectField.label}
              placeholder={casesubjectField.placeholder}
              value=""
              validationRules={casesubjectField.validationRules}
              register={register}
              errors={errors}
              checkNameFormat
              setValue={setValue}
              onChangeHandler={handleInputChange}
            />
            <div className="form__group">
              <label className="form__field-label">{casedescField.label}</label>
              <textarea
                name="Description"
                rows={3}
                cols={30}
                value={description}
                placeholder={casedescField.placeholder}
                onChange={(e) => setDescription(e.target.value)}
              />
            </div>

            <div className="form__group">
              <label className="form__field-label">
                {attachmentfield.label}
              </label>
              <FileBase64
                hidden
                id="vida-upload-documents__upload-btn"
                type="file"
                multiple={false}
                onDone={(file) => handleFileinput(file)}
              />
            </div>
            <button
              type="submit"
              className="btn btn--primary btn--lg"
              onClick={handleSubmit}
            >
              {nextBtn.label}
            </button>
          </form>
        </>
      )}
    </>
  );
};

ComplainForm.propTypes = {
  config: PropTypes.shape({
    agreeTerms: PropTypes.shape({
      agreeLabel: PropTypes.string,
      btnLabel: PropTypes.shape({
        agree: PropTypes.string,
        close: PropTypes.string
      }),
      id: PropTypes.string,
      terms: PropTypes.shape({
        actionUrl: PropTypes.string,
        label: PropTypes.string
      }),
      validationRules: PropTypes.shape({
        required: PropTypes.shape({
          message: PropTypes.string
        })
      })
    }),
    bookingTitle: PropTypes.string,
    cityField: PropTypes.shape({
      label: PropTypes.string,
      validationRules: PropTypes.object
    }),
    systemfield: PropTypes.shape({
      label: PropTypes.string,
      validationRules: PropTypes.object
    }),
    categoryfield: PropTypes.shape({
      label: PropTypes.string,
      validationRules: PropTypes.object
    }),
    subcategoryfield: PropTypes.shape({
      label: PropTypes.string,
      validationRules: PropTypes.object
    }),
    firstNameField: PropTypes.shape({
      label: PropTypes.string,
      placeholder: PropTypes.string,
      validationRules: PropTypes.object
    }),
    attachmentfield: PropTypes.shape({
      label: PropTypes.string,
      placeholder: PropTypes.string
    }),
    casesubjectField: PropTypes.shape({
      label: PropTypes.string,
      placeholder: PropTypes.string,
      validationRules: PropTypes.object
    }),
    casedescField: PropTypes.shape({
      label: PropTypes.string,
      placeholder: PropTypes.string,
      validationRules: PropTypes.object
    }),
    phoneNumberField: PropTypes.shape({
      label: PropTypes.string,
      placeholder: PropTypes.string,
      validationRules: PropTypes.object
    }),
    emailField: PropTypes.shape({
      label: PropTypes.string,
      infoLabel: PropTypes.string,
      placeholder: PropTypes.string,
      validationRules: PropTypes.object
    }),
    notificationField: PropTypes.shape({
      label: PropTypes.string,
      message: PropTypes.string
    }),
    nextBtn: PropTypes.shape({
      label: PropTypes.string
    }),
    errormsg: PropTypes.shape({
      success: PropTypes.string,
      failure: PropTypes.string,
      congratsmsg: PropTypes.string,
      casemsg: PropTypes.string
    })
  })
};

ComplainForm.defaultProps = {
  config: {}
};

export default ComplainForm;
