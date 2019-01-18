ALTER TABLE organization ADD COLUMN advertbannerid INT(11) DEFAULT NULL AFTER cscontactid;
--删除机构后将其状态改为9，不再删除记录
ALTER TABLE organization DROP INDEX `name`;