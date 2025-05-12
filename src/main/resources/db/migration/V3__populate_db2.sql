
INSERT INTO users(id,username, password, role) values ('61d8cc94-32bc-490a-bc97-527611f38188','testuser1', '$2y$12$rJsbLv5LqHGGRxK5y0BS9ubOkoQkfhUBDX8g5gZ0AVs4HO4uDfRyq', 'admin');
INSERT INTO users(id,username, password, role) values ('1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2','testuser2', '$2y$12$rJsbLv5LqHGGRxK5y0BS9ubOkoQkfhUBDX8g5gZ0AVs4HO4uDfRyq', 'user');
INSERT INTO users(id,username, password, role) values ('741acf2f-768a-4083-b50e-ab42a0df6292','testuser3', '$2y$12$rJsbLv5LqHGGRxK5y0BS9ubOkoQkfhUBDX8g5gZ0AVs4HO4uDfRyq', 'admin');

INSERT INTO issues(id, owner_id, title) values ('32eaa6fe-6195-4486-aaf4-bc5576250085', '61d8cc94-32bc-490a-bc97-527611f38188', 'titel1');
INSERT INTO issues(id, owner_id, title) values ('058e8b9f-89aa-4b92-ac59-f8d3149e2b1d', '61d8cc94-32bc-490a-bc97-527611f38188', 'titel1');
INSERT INTO issues(id, owner_id, title) values ('d4324583-bb2e-4643-9788-eb42f41537ee', '61d8cc94-32bc-490a-bc97-527611f38188', 'titel1');

INSERT INTO issues(id, owner_id, title) values ('8c909cf6-95b1-40d5-a016-73c0d875ef2c', '1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2', 'titel2');
INSERT INTO issues(id, owner_id, title) values ('8cd03c51-24b3-4f9b-be27-752136d258d6', '1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2', 'titel2');
INSERT INTO issues(id, owner_id, title) values ('10084d2d-d046-46aa-9672-99ca870d648d', '1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2', 'titel2');

INSERT INTO issues(id, owner_id, title) values ('bb4cc0ab-0c9f-4cb8-968e-76890a3930c8', '741acf2f-768a-4083-b50e-ab42a0df6292', 'titel3');
INSERT INTO issues(id, owner_id, title) values ('adbe2baf-0efd-4216-9059-bdc80c8ea296', '741acf2f-768a-4083-b50e-ab42a0df6292', 'titel3');
INSERT INTO issues(id, owner_id, title) values ('8a975008-6543-48b0-9d00-9c50104d5e35', '741acf2f-768a-4083-b50e-ab42a0df6292', 'titel3');

INSERT INTO comments(id, body, issue_id, user_id) values ('91d638ea-aafc-4450-9f9c-0b58a32d9e01', 'comment1user1', '32eaa6fe-6195-4486-aaf4-bc5576250085','61d8cc94-32bc-490a-bc97-527611f38188');
INSERT INTO comments(id, body, issue_id, user_id) values ('493fc125-5590-44b3-89ab-c52068929921', 'comment2user1', '32eaa6fe-6195-4486-aaf4-bc5576250085','61d8cc94-32bc-490a-bc97-527611f38188');

INSERT INTO comments(id, body, issue_id, user_id) values ('617bf5ec-b019-4d35-874c-fc3bc8e74a57', 'comment1user2', '058e8b9f-89aa-4b92-ac59-f8d3149e2b1d','61d8cc94-32bc-490a-bc97-527611f38188');
INSERT INTO comments(id, body, issue_id, user_id) values ('829adc21-7529-4208-847d-6fdabb0bf137', 'comment2user2', '058e8b9f-89aa-4b92-ac59-f8d3149e2b1d','61d8cc94-32bc-490a-bc97-527611f38188');

INSERT INTO comments(id, body, issue_id, user_id) values ('6034a1f5-5209-42eb-ad4c-7d3c86d8dd12', 'comment1user3', 'd4324583-bb2e-4643-9788-eb42f41537ee','61d8cc94-32bc-490a-bc97-527611f38188');
INSERT INTO comments(id, body, issue_id, user_id) values ('66b7c76b-9796-4cb4-8c43-1790b4541ee8', 'comment2user3', 'd4324583-bb2e-4643-9788-eb42f41537ee','61d8cc94-32bc-490a-bc97-527611f38188');

INSERT INTO comments(id, body, issue_id, user_id) values ('4de65f0b-7003-4b71-b92b-5855e41601a0', 'comment1user4', '8c909cf6-95b1-40d5-a016-73c0d875ef2c','1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2');
INSERT INTO comments(id, body, issue_id, user_id) values ('dda058e1-dc04-42f0-9b99-b5a70dd0f6e4', 'comment2user4', '8c909cf6-95b1-40d5-a016-73c0d875ef2c','1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2');

INSERT INTO comments(id, body, issue_id, user_id) values ('b7057be4-671b-4940-b0cc-6b4b8c820769', 'comment1user5', '8cd03c51-24b3-4f9b-be27-752136d258d6','1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2');
INSERT INTO comments(id, body, issue_id, user_id) values ('263f17eb-1706-4770-ae36-a934179c03cd', 'comment2user5', '8cd03c51-24b3-4f9b-be27-752136d258d6','1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2');

INSERT INTO comments(id, body, issue_id, user_id) values ('2471b593-a82b-4516-9e9f-89bffe682a1f', 'comment1user6', '10084d2d-d046-46aa-9672-99ca870d648d','1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2');
INSERT INTO comments(id, body, issue_id, user_id) values ('a96fc144-f116-4104-81ad-bb6b772f43d6', 'comment2user6', '10084d2d-d046-46aa-9672-99ca870d648d','1ff0e8d0-64a1-4900-aeb1-b70c9dadbdd2');

INSERT INTO comments(id, body, issue_id, user_id) values ('86bae86d-1e4b-4040-9490-8423856ba712', 'comment1user7', 'bb4cc0ab-0c9f-4cb8-968e-76890a3930c8','741acf2f-768a-4083-b50e-ab42a0df6292');
INSERT INTO comments(id, body, issue_id, user_id) values ('67912a96-1aad-4759-9543-ac4104efd40b', 'comment2user7', 'bb4cc0ab-0c9f-4cb8-968e-76890a3930c8','741acf2f-768a-4083-b50e-ab42a0df6292');

INSERT INTO comments(id, body, issue_id, user_id) values ('b56c1571-12c7-46bc-a18e-161931d851da', 'comment1user8', 'adbe2baf-0efd-4216-9059-bdc80c8ea296','741acf2f-768a-4083-b50e-ab42a0df6292');
INSERT INTO comments(id, body, issue_id, user_id) values ('1d42b170-0d1a-4749-a1f8-b2a5d60c37f2', 'comment2user8', 'adbe2baf-0efd-4216-9059-bdc80c8ea296','741acf2f-768a-4083-b50e-ab42a0df6292');

INSERT INTO comments(id, body, issue_id, user_id) values ('b00fca8b-4230-4786-b047-635a2463526b', 'comment1user9', '8a975008-6543-48b0-9d00-9c50104d5e35','741acf2f-768a-4083-b50e-ab42a0df6292');
INSERT INTO comments(id, body, issue_id, user_id) values ('509f815e-ca08-4e0b-881f-c50c65ee26ae', 'comment2user9', '8a975008-6543-48b0-9d00-9c50104d5e35','741acf2f-768a-4083-b50e-ab42a0df6292');