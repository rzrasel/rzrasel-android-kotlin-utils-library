<?php
class DbMenuTypeModel extends DbMenuTypeModelProperty {
    public $id              = parent::propId;
    public $serial          = parent::propSerial;
    public $name            = parent::propName;
    public $slug            = parent::propSlug;
    public $isEnabled       = parent::propIsEnabled;
    public $createDate      = parent::propCreateDate;
    public $modifiedDate    = parent::propModifiedDate;
}
?>