
        $(document).ready(function(){

        // 1. Hide Error Section by default
           $("#shipmentModeError").hide();
           $("#shipmentCodeError").hide();
           $("#enableShipmentError").hide();
           $("#shipmentGradeError").hide();
           $("#shipmentDescriptionError").hide();
         //2. Create Error Variable
           var shipmentModeError= false;
           var shipmentCodeError=false;
           var enableShipmentError=false;
           var shipmentGradeError=false;
           var shipmentDescriptionError=false;
         //3. Create Validate Function
           function validate_shipmentMode(){
            
           var val=$("#shipmentMode").val();
           if(val=='') //if its empty
           {
            $("#shipmentModeError").show(); //show error section
            $("#shipmentModeError").html("<b>Please Select One Option</b>"); //Error Msg
            $("#shipmentModeError").css("color","red");
            shipmentModeError=false;
           }
           else
           {
            $("#shipmentModeError").hide(); 
            shipmentModeError = true;
           }

            return shipmentModeError;
           };


           function validate_shipmentCode()
           {
            var val=$("#shipmentCode").val();
            var exp=/^[A-Z0-9\.\-]{4,12}$/;  //Regular Expression
            if(val=='')
            {
                $("#shipmentCodeError").show();
                $("#shipmentCodeError").html("<b>Please Enter Shipment Code</b>");
                $("#shipmentCodeError").css("color","red");
                shipmentCodeError=false;
            }
            else if(!exp.test(val))
            {
                $("#shipmentCodeError").show();
                $("#shipmentCodeError").html("<b>Invalid Shipment Code</b>");
                $("#shipmentCodeError").css("color","red");
                shipmentCodeError=false;
            }
            else
            {
                $("#shipmentCodeError").hide();
                shipmentCodeError=true;
            }
            return shipmentCodeError;
           }

           function validate_enableShipment()
           {
               var len=$('[name="enableShipment"]:checked').length; // no of selected options
               if(len==0)
               {
                   $("#enableShipmentError").show();
                   $("#enableShipmentError").html("<b>Please Choose One Option</b>");
                   $("#enableShipmentError").css("color","red");
                   enableShipmentError=false;
               }
               else{
                  $("#enableShipmentError").hide();
                  enableShipmentError=true;

               }
               return enableShipmentError;
           }

           function validate_shipmentGrade()
           {
              var len=$('[name="shipmentGrade"]:checked').length;
              if(len==0)
              {
                  $("#shipmentGradeError").show();
                  $("#shipmentGradeError").html("<b>Please Choose One Grade</b>");
                  $("#shipmentGradeError").css("color","red");
                  shipmentGradeError=false;

              }
              else
              {
                $("#shipmentGradeError").hide();
                shipmentGradeError=true;
              }
              return shipmentGradeError;
           }


           function validate_shipmentDescription()
           {
               var val=$("#shipmentDescription").val();
               var exp=/^[A-Za-z0-9\.\,\s\-]{5,150}$/;
               if(val=='')
               {
                   $("#shipmentDescriptionError").show();
                   $("#shipmentDescriptionError").html("<b>Please Enter Description</b>");
                   $("#shipmentDescriptionError").css("color","red");
                   shipmentDescriptionError=false;

               }
               else if(!exp.test(val))
               {
                   $("#shipmentDescriptionError").show();
                   $("#shipmentDescriptionError").html("<b>Description must be 5-150 chars only</b>");
                   $("#shipmentDescriptionError").css("color","red");
                   shipmentDescriptionError=false;
               }
               else
               {
                $("#shipmentDescriptionError").hide();
                shipmentDescriptionError=true;
               }
               return shipmentDescriptionError;
           }
         //4. Link with Action Event
           $("#shipmentMode").change(function(){
            validate_shipmentMode();
           });
           $("#shipmentCode").keyup(function(){
            $(this).val($(this).val().toUpperCase());
            validate_shipmentCode();
          })
          $('[name="enableShipment"]').change(function(){
            validate_enableShipment();
          });
          $('[name="shipmentGrade"]').change(function(){
            validate_shipmentGrade();
          });
          $("#shipmentDescription").keyup(function(){
            validate_shipmentDescription();
          });
         //5. On Form Submit
           $("#shipmentRegForm").submit(function(){

            validate_shipmentMode();
            validate_shipmentCode();
            validate_enableShipment();
            validate_shipmentGrade();
            validate_shipmentDescription();

            if(shipmentModeError && shipmentCodeError && enableShipmentError &&
               shipmentGradeError && shipmentDescriptionError
            )           
            {
                return true; //submit form
            }
            else
            {
                return false; //dont submit form
            }
           });
        });

