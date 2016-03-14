--Q1
select l.lion_id from lion l where SDO_FILTER(l.lionpos, SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(150,200,400,350)),'querytype=WINDOW')='TRUE';
--Q2
select l.lion_id from lion l, pond p where SDO_WITHIN_DISTANCE(l.lionpos,p.pshape,'distance=150')='TRUE' AND p.pond_id in('P1');
--Q3
select p.pond_id,SDO_NN_DISTANCE(1) dist from lion l, pond p where sdo_nn(p.pshape,l.lionpos,'sdo_num_res=3',1)='TRUE' and l.lion_id='L2' order by dist;
--Q4
select l.lion_id, p.pond_id from table(sdo_join('pond','pshape','lion','lionpos','mask=INSIDE'))c, lion l, pond p where c.rowid2=l.rowid AND c.rowid1=p.rowid;
--Q5
select distinct r2.reg_id from region r2, lion l1, (select r1.reg_id from region r1 where r1.reg_id not in(select r.reg_id from region r, pond p where sdo_relate(p.pshape, r.rshape,'mask=INSIDE')='TRUE')) c where sdo_relate(l1.lionpos, r2.rshape, 'mask=INSIDE')='TRUE' and r2.reg_id=c.reg_id;
--Q6
select distinct l1.lion_id from lion l1 where l1.lion_id not in (select l.lion_id from lion l, ambulance a where sdo_relate(l.lionpos, a.ashape, 'mask=INSIDE')='TRUE');
